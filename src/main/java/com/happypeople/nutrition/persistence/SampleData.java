package com.happypeople.nutrition.persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.happypeople.nutrition.model.Food;
import com.happypeople.nutrition.model.FoodAmount;
import com.happypeople.nutrition.model.FoodAmount.FoodUnit;
import com.happypeople.nutrition.model.NutritionListEntry;

/** Some sample data for testing. */
class SampleData {

	private static <T> List<T> mkList(final T... objects) {
		final List<T> list=new ArrayList<T>();
		for(final T t : objects)
			list.add(t);
		return list;
	}

	public final List<Food> foods=mkList(
			mkFood("Milch 1,5%", 46, 1.5, 4.7, 3.4),
			mkFood("Joghurt 0,1%", 39, 0.1, 5.4, 4.2),
			mkFood("Körniger Frischkäse 0,4%", 72, 0.4, 4.0, 12.7),
			mkFood("Kräuterquark 2,4%", 75, 2.4, 4.0, 8.8),
			mkFood("Kräuterquark 40%", 141, 10.1, 3.6, 8.5),
			mkFood("Basismüsli 5-Korn-Mix", 366, 7.6, 56.0, 12.0),
			mkFood("Knuspermüsli Granola", 446, 15.5, 63.5, 9.6),
			mkFood("Thunfisch", 118, 1.5, 0.0, 26.0),
			mkFood("Rosinen", 320, 0.5, 73.0, 2.8),
			mkFood("Öl Raps", 900, 100.0, 0.0, 0.0),
			mkFood("Energy Cake Joghurt", 448, 19.7, 58.2, 6.4),
			mkFood("Energy Cake Original", 437, 18.7, 58.0, 6.4),
			mkFood("Energy Cake Toffee", 444, 18.4, 62.9, 5.7)
	);

	static SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static Date d(final String dateStr) {
		try {
			return df.parse(dateStr);
		} catch (final ParseException e) {
			throw new RuntimeException("should not happen", e);
		}
	}

	/** some sample nutrition for testing
	 */
	public final List<NutritionListEntry> sampleNutritions=mkList(
		mkNutri("2017-09-18 09:30", foods.get(0), 250, FoodUnit.GRAM),
		mkNutri("2017-09-18 11:30", foods.get(5),  20, FoodUnit.GRAM),
		mkNutri("2017-09-18 12:00", foods.get(2), 150, FoodUnit.KCAL),
		mkNutri("2017-09-18 18:00", foods.get(7), 125, FoodUnit.GRAM),
		mkNutri("2017-09-17 08:00", foods.get(4), 250, FoodUnit.GRAM),
		mkNutri("2017-09-17 18:00", foods.get(7), 125, FoodUnit.GRAM),
		mkNutri("2017-09-17 18:00", foods.get(3), 200, FoodUnit.GRAM)
	);

	private static NutritionListEntry mkNutri(final String date, final Food food, final int amount, final FoodUnit unit) {
		return new NutritionListEntry(d(date), food, new FoodAmount(amount, unit));
	}

	private static Food mkFood(final String name, final int kcalPer100g,
			final double fatGrams, final double carboGrams, final double proteinGrams) {
		final double kCalFat=930*fatGrams;
		final double kCalCarbo=410*carboGrams;
		final double kCalProtein=410*proteinGrams;
		final double kCalSum=kCalFat+kCalCarbo+kCalProtein;

		// adjust kCalSum to kcalPer100g. Differences caused by rounding errors.
		final double multiplikator=kcalPer100g/kCalSum;

		return new Food(name,
				kCalFat*multiplikator,
				kCalCarbo*multiplikator,
				kCalProtein*multiplikator);
	}
}
