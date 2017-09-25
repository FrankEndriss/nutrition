package com.happypeople.jsftutorial.beans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.happypeople.nutrition.model.Food;
import com.happypeople.nutrition.model.FoodAmount;
import com.happypeople.nutrition.model.FoodAmount.FoodUnit;
import com.happypeople.nutrition.model.NutritionListEntry;
import com.happypeople.persistence.DataRepository;

@ManagedBean(name="editNutritionController")
@SessionScoped
public class EditNutritionController implements Serializable {

	@ManagedProperty(value="#{dataRepository}")
	private DataRepository dataRepository;

	public void setDataRepository(final DataRepository dataRepository) {
		this.dataRepository = dataRepository;
	}

	private enum Mode {
		UPDATE,
		CREATE
	}

	private Mode mode;

	private Date tsDate;
	private Date tsTime;
	private String foodStr;
	private int amount;
	private String amountUnitStr;

	public boolean isCreateMode() {
		return mode==Mode.CREATE;
	}

	public boolean isUpdateMode() {
		return mode==Mode.UPDATE;
	}

	/** Routes to the edit page in CREATE-Mode.
	 * @return View name for editing a nutrition.
	 */
	public String route2Create() {
		mode=Mode.CREATE;
		tsDate=new Date();
		tsTime=new Date();
		return "editNutritionEntry";
	}

	private final static DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	/** Sets the date part of the timestamp, format="yyyy-MM-dd".
	 * Need to be validated to above format, must not be null.
	 * @param dateStr The Date of the input
	 * @throws ParseException If not parseable
	 */
	public void setDate(final String dateStr) throws ParseException {
		tsDate=dateFormat.parse(dateStr);
	}
	public String getDate() {
		return tsDate!=null?dateFormat.format(tsDate):"";
	}

	private final static DateFormat timeFormat=new SimpleDateFormat("HH:mm");
	/** Sets the time part of the timestamp, format="HH:mm".
	 * Need to be validated to above format, must not be null.
	 * @param dateStr The Date of the input
	 * @throws ParseException If not parseable
	 */
	public void setTime(final String timeStr) throws ParseException {
		tsTime=timeFormat.parse(timeStr);
	}
	public String getTime() {
		return tsTime!=null?timeFormat.format(tsTime):"";
	}

	private final static DateFormat dateAndTimeFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public Date getDateAndTime() throws ParseException {
		final String str=getDate()+" "+getTime();
		return dateAndTimeFormat.parse(str);
	}

	/**
	 * @param foodStr
	 */
	public void setFoodStr(final String foodStr) {
		this.foodStr = foodStr;
	}
	public String getFoodStr() {
		return foodStr;
	}

	public void setAmount(final int amount) {
		this.amount = amount;
	}
	public int getAmount() {
		return amount;
	}

	public void setAmountUnitStr(final String amountUnitStr) {
		this.amountUnitStr = amountUnitStr;
	}
	public String getAmountUnitStr() {
		return amountUnitStr;
	}

	public List<Food> getAllFoods() {
		final List<Food> foods=dataRepository.getFoods();
		foods.sort(new Comparator<Food>() {
			@Override
			public int compare(final Food o1, final Food o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		return foods;
	}

	public FoodUnit[] getAmountUnits() {
		return FoodUnit.values();
	}

	public String createNutritionEntry() throws ParseException {
		final NutritionListEntry ent=new NutritionListEntry(getDateAndTime(),
				dataRepository.getFoodByName(foodStr).orElseThrow(() -> new IllegalStateException("unknown food")),
				new FoodAmount(amount, FoodUnit.valueOf(amountUnitStr)));

		System.out.println("created NutritionEntry: "+tsDate+" "+getFoodStr()+" "+getAmount());
		dataRepository.createNutritionListEntry(ent);
		return "home";
	}

	public String updateNutritionEntry() {
		System.out.println("update NutritionEntry: "+tsDate+" "+getFoodStr()+" "+getAmount());
		return "home";
	}
}
