package com.happypeople.jsftutorial.beans;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.happypeople.nutrition.model.NutritionListEntry;
import com.happypeople.persistence.DataRepository;

@ManagedBean(name="helloworldBean")
@SessionScoped
public class HelloworldBean implements Serializable {

	@ManagedProperty(value = "#{dataRepository}")
	private DataRepository dataRepository;

	public void setDataRepository(final DataRepository dataRepository) {
		this.dataRepository = dataRepository;
	}

	public HelloworldBean() {
		// empty
	}

	public String getMessage() {
		return "Franky says 'Hello World!'";
	}

	public List<NutritionListEntry> getNutritionData() {
		return dataRepository.getNutritionListEntries();
	}

	private final SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	/** Cuts the time part of the java.util.Date
	 * @param date The date to cut the time out
	 * @return The same date, but without time, ie default time instead.
	 */
	private Date cutTime(final Date date) {
		try {
			return dateFormat.parse(dateFormat.format(date));
		} catch (final ParseException e) {
			// should not happen by design
			throw new RuntimeException("Oups", e);
		}
	}

	public List<NutritionListDayGroup> getNutritionDataByDate() {

		final Map<Date, List<NutritionListEntry>> map=new HashMap<Date, List<NutritionListEntry>>();
		for(final NutritionListEntry ent : getNutritionData()) {
			map.putIfAbsent(cutTime(ent.getTs()), new ArrayList<NutritionListEntry>());
			map.get(cutTime(ent.getTs())).add(ent);
		}

		final List<NutritionListDayGroup> list=new ArrayList<NutritionListDayGroup>();
		for(final Date d : map.keySet()) {
			list.add(new NutritionListDayGroup() {

				@Override
				public Date getDate() {
					return d;
				}

				@Override
				public List<NutritionListEntry> getNutritionData() {
					return map.get(d);
				}

				@Override
				public int getSumKcal() {
					int sum=0;
					for(final NutritionListEntry ent : getNutritionData())
						sum+=ent.getKcal();
					return sum;
				}
			});
		}

		list.sort(new Comparator<NutritionListDayGroup>() {
			@Override
			public int compare(final NutritionListDayGroup o1, final NutritionListDayGroup o2) {
				// reverse date order
				return o2.getDate().compareTo(o1.getDate());
			}
		});

		return list;
	}

	public interface NutritionListDayGroup {
		public Date getDate();
		public List<NutritionListEntry> getNutritionData();
		public int getSumKcal();
	}
}
