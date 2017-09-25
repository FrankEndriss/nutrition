package com.happypeople.nutrition.model;

/** Immutable data class representing foods and ingredients.
 * Ingredients are categorized as fat, carbohydrates and proteins.
 */
public class Food {

    private final String name;

    private final double kcalPer100gFat;
    private final double kcalPer100gCarbo;
    private final double kcalPer100gProtein;

    public Food(final String name, final double kcalPer100gFat, final double kcalPer100gCarbo, final double kcalPer100gProtein) {
        this.name=name;
        this.kcalPer100gFat=kcalPer100gFat;
        this.kcalPer100gCarbo=kcalPer100gCarbo;
        this.kcalPer100gProtein=kcalPer100gProtein;
    }

    /** @return Name of this food. */
    public String getName() {
        return name;
    }

    /** @return The rounded kCal per 100g value, calculated as the
     * sum of the other three values.
     */
    public int getKcalPer100g() {
    	// round to int
    	return (int)((kcalPer100gFat+kcalPer100gCarbo+kcalPer100gProtein)+.5);
    }

    /** @return How much per cent of the calories of this food are carbohydrates? */
    public double getKcalPer100gCarbo() {
        return kcalPer100gCarbo;
    }

    /** @return How much per cent of the calories of this food is fat? */
    public double getKcalPer100gFat() {
        return kcalPer100gFat;
    }

    /** @return How much per cent of the calories of this food is protein? */
    public double getKcalPer100gProtein() {
        return kcalPer100gProtein;
    }
}
