package com.happypeople.nutrition.model;

import java.util.Date;

/** Immutable data objects,
 */
public class NutritionListEntry {

    /** Timestamp */
    private final Date ts;
    /** Which food */
    private final Food food;
    /** How much of that food */
    private final FoodAmount amount;

    public NutritionListEntry(final Date ts, final Food food, final FoodAmount amount) {
        this.ts=ts;
        this.food=food;
        this.amount=amount;
    }

    public Date getTs() {
        return ts;
    }

    public Food getFood() {
        return food;
    }

    public int getKcal() {
        return amount.getKcal(food);
    }

    public int getGrams() {
        return amount.getGrams(food);
    }

}
