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

import com.ibm.spss.boot.web.sitemesh.SitemeshFilter;

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
	public FilterRegistrationBean sitemesh3() {
	    FilterRegistrationBean registration = new FilterRegistrationBean();
	    registration.setFilter(new SitemeshFilter());
	    registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
	    return registration;
	}
}
