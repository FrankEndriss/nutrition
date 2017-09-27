package com.happypeople.nutrition.persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.happypeople.nutrition.model.Food;
import com.happypeople.nutrition.model.FoodAmount.FoodUnit;
import com.happypeople.nutrition.model.NutritionListEntry;

/** Simple persistence/datastore interface/implementation.
 */
//@Component(value="dataRepository")
//@ManagedBean(name="dataRepository")
//@ApplicationScoped
public class DataRepository {
	/** SampleData for testing. */
	private final SampleData sampleData=new SampleData();

	private final static Comparator<Food> foodNameComparator=new Comparator<Food>() {
		@Override
		public int compare(final Food o1, final Food o2) {
			return o1.getName().compareTo(o2.getName());
		}
	};

	public List<FoodUnit> getAmountUnits() {
		return Arrays.asList(FoodUnit.values());
	}

	/** @return All existing foods, sorted by name. */
	public List<Food> getFoods() {
		final List<Food> ret=new ArrayList<Food>();
		ret.addAll(sampleData.foods);
		ret.sort(foodNameComparator);
		return ret;
	}

	public Optional<Food> getFoodByName(final String name) {
		for(final Food food : sampleData.foods) {
			if(food.getName().equals(name))
				return Optional.of(food);
		}
		return Optional.empty();
	}

	public void createFood(final Food food) {
		sampleData.foods.add(food);
	}

	public void updateFood(final Food food) {
		throw new RuntimeException("not implemented yet");
	}

	public List<NutritionListEntry> getNutritionListEntries() {
		final List<NutritionListEntry> ret=new ArrayList<NutritionListEntry>();
		ret.addAll(sampleData.sampleNutritions);
		return ret;
	}

	public void createNutritionListEntry(final NutritionListEntry nut) {
		sampleData.sampleNutritions.add(nut);
	}

	public void updateNutritionListEntry(final NutritionListEntry nut) {
		throw new RuntimeException("not implemented yet");
	}

	public void deleteNutritionListEntry(final NutritionListEntry ent) {
		throw new RuntimeException("not implemented yet");
	}

}
