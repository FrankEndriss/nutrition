package com.happypeople.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.happypeople.nutrition.model.Food;
import com.happypeople.nutrition.model.FoodAmount.FoodUnit;
import com.happypeople.nutrition.model.NutritionListEntry;

/** Simple persistence/datastore interface/implementation.
 */
@ManagedBean(name="dataRepository")
@ApplicationScoped
public class DataRepository implements Serializable {

	/** SampleData for testing. */
	private final SampleData sampleData=new SampleData();

	public List<FoodUnit> getAmountUnits() {
		return Arrays.asList(FoodUnit.values());
	}

	public List<Food> getFoods() {
		final List<Food> ret=new ArrayList<Food>();
		ret.addAll(sampleData.foods);
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
		throw new RuntimeException("not implemented yet");
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
