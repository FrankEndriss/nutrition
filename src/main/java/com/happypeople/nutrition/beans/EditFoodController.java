package com.happypeople.nutrition.beans;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.happypeople.nutrition.model.Food;
import com.happypeople.nutrition.persistence.DataRepository;

/** Simple controller for editFood.xhtml form and save-action methods.
 */
public class EditFoodController implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private int kcal;
	private double fatPer100;
	private double carboPer100;
	private double sugarPer100;
	private double proteinPer100;

	private Mode mode=Mode.CREATE;
	@Autowired
	private DataRepository dataRepository;

	public String route2CreateFood() {
		mode=Mode.CREATE;
		return "editFood";
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	/** Called to create a new Food
	 * @return "editNutrition"
	 */
	public String createFood() {
		final Food newFood=new Food(name, kcal, fatPer100, carboPer100, sugarPer100, proteinPer100);
		dataRepository.createFood(newFood);
		System.out.println("new Food created");
		return "editNutritionEntry";
	}

	/** Called to update an existing Food
	 * @return "editNutrion"
	 */
	public String updateFood() {
		System.out.println("in updateFood()");
		return "editNutritionEntry";
	}

	private enum Mode {
		UPDATE,
		CREATE
	}

	public boolean isCreateMode() {
		return mode==Mode.CREATE;
	}

	public int getKcal() {
		return kcal;
	}

	public void setKcal(final int kcal) {
		this.kcal = kcal;
	}

	public double getFatPer100() {
		return fatPer100;
	}

	public void setFatPer100(final double fatPer100) {
		this.fatPer100 = fatPer100;
	}

	public double getCarboPer100() {
		return carboPer100;
	}

	public void setCarboPer100(final double carboPer100) {
		this.carboPer100 = carboPer100;
	}

	public double getSugarPer100() {
		return sugarPer100;
	}

	public void setSugarPer100(final double sugarPer100) {
		this.sugarPer100 = sugarPer100;
	}

	public double getProteinPer100() {
		return proteinPer100;
	}

	public void setProteinPer100(final double proteinPer100) {
		this.proteinPer100 = proteinPer100;
	}

	public Mode getMode() {
		return mode;
	}
}
