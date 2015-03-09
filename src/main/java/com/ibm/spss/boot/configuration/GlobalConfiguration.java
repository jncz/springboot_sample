package com.ibm.spss.boot.configuration;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
public class GlobalConfiguration {

	@Autowired
	private ErrorAttributes attr;
	
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer(){
	    return new ErrorCustomizer();
	}

	private static class ErrorCustomizer implements EmbeddedServletContainerCustomizer {

	    @Override
	    public void customize(ConfigurableEmbeddedServletContainer container) {
	        container.addErrorPages(new ErrorPage("/error/error.html"));
	    }

	}
	
	@Bean
	public FilterRegistrationBean myFilter() {
	    FilterRegistrationBean registration = new FilterRegistrationBean();
	    registration.setFilter(new HiddenHttpMethodFilter());
	    registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
	    return registration;
	}
}
