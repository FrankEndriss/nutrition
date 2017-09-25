package com.happypeople.nutrition;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {
//public class ServletInitializer {
	 @Override
	  protected SpringApplicationBuilder configure( final SpringApplicationBuilder application )
	  {
	    return application.sources( SimpleNutritionHelperWebappApplication.class );
	  }
}
