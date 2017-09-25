package com.happypeople.nutrition;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sun.faces.config.FacesInitializer;

/** copied from
 * https://www.leveluplunch.com/java/tutorials/037-integrate-jsf-spring-boot/
 */
@Configuration
public class ConfigureJSF {
	@Bean
	public ServletRegistrationBean facesServletRegistration() {
		final ServletRegistrationBean servletRegistrationBean = new JsfServletRegistrationBean();
		return servletRegistrationBean;
	}

	public class JsfServletRegistrationBean extends ServletRegistrationBean {

		public JsfServletRegistrationBean() {
			super();
		}

		@Override
		public void onStartup(final ServletContext servletContext) throws ServletException {

			final FacesInitializer facesInitializer = new FacesInitializer();

			final Set<Class<?>> clazz = new HashSet<Class<?>>();
			clazz.add(ConfigureJSF.class);
			facesInitializer.onStartup(clazz, servletContext);
		}
	}
}
