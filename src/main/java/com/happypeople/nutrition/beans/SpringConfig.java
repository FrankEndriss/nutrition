package com.happypeople.nutrition.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.happypeople.nutrition.persistence.DataRepository;

@Configuration
public class SpringConfig {

	@Bean
	@Scope("session")
	public EditNutritionController editNutritionController() {
		return new EditNutritionController();
	}

	@Bean
	@Scope("session")
	public HelloworldBean helloworldBean() {
		return new HelloworldBean();
	}

	@Bean
	@Scope("session")
	public EditFoodController editFoodController() {
		return new EditFoodController();
	}

	@Bean
	@Scope("singleton")
	public DataRepository dataRepository() {
		return new DataRepository();
	}

}
