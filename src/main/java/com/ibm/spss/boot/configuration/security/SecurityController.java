package com.ibm.spss.boot.configuration.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * This class is used for customizing the login page. use the login.html as the login page. but mapping the html to "/login"
 * @author liping
 *
 */
@Controller
public class SecurityController extends WebMvcConfigurerAdapter {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}

}