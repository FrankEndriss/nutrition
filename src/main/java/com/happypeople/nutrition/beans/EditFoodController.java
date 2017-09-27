package com.happypeople.nutrition.beans;

import java.io.Serializable;

/** Simple controller for editFood.xhtml form and save-action methods.
 */
public class EditFoodController implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private int kcal;
	private int fatPer100;
	private int carboPer100;
	private int sugarPer100;
	private int proteinPer100;

	private final Mode mode=Mode.CREATE;

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

		return "editNutrition";
	}

	/** Called to update an existing Food
	 * @return "editNutrion"
	 */
	public String updateFood() {

		return "editNutrition";
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

	public int getFatPer100() {
		return fatPer100;
	}

	public void setFatPer100(final int fatPer100) {
		this.fatPer100 = fatPer100;
	}

	public int getCarboPer100() {
		return carboPer100;
	}

	public void setCarboPer100(final int carboPer100) {
		this.carboPer100 = carboPer100;
	}

	public int getSugarPer100() {
		return sugarPer100;
	}

	public void setSugarPer100(final int sugarPer100) {
		this.sugarPer100 = sugarPer100;
	}

	public int getProteinPer100() {
		return proteinPer100;
	}

	public void setProteinPer100(final int proteinPer100) {
		this.proteinPer100 = proteinPer100;
	}

	public Mode getMode() {
		return mode;
	}
}
