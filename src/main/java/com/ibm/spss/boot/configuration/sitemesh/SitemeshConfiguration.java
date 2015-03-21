package com.ibm.spss.boot.configuration.sitemesh;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SitemeshConfiguration {
	@Bean
	public FilterRegistrationBean sitemesh3() {
	    FilterRegistrationBean registration = new FilterRegistrationBean();
	    registration.setFilter(new SitemeshFilter());
	    registration.setOrder(2);
	    registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
	    return registration;
	}
}
