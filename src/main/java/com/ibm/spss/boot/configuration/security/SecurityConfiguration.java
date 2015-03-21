package com.ibm.spss.boot.configuration.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.ibm.spss.boot.util.RunningProfile;

@EnableAutoConfiguration
@ComponentScan
public class SecurityConfiguration {
	
    @Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    @Profile(RunningProfile.Security)
	protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {

		@Autowired
		private SecurityProperties security;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable().authorizeRequests().anyRequest().fullyAuthenticated()
			.and()
			.formLogin()
					.loginPage("/login").failureUrl("/login?error").permitAll().defaultSuccessUrl("/pages/admin/admin.html",true);
		}

		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication().withUser("admin").password("admin")
					.roles("ADMIN", "USER").and().withUser("user").password("user")
					.roles("USER");
		}

	}
    
    @Configuration
   	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    @ConditionalOnMissingBean(ApplicationSecurity.class)
   	protected static class EmptyApplicationSecurity extends WebSecurityConfigurerAdapter {

   		@Autowired
   		private SecurityProperties security;

   		@Override
   		protected void configure(HttpSecurity http) throws Exception {
   			http.authorizeRequests().anyRequest().permitAll();
   		}

   		@Override
   		public void configure(AuthenticationManagerBuilder auth) throws Exception {
   			auth.inMemoryAuthentication().withUser("admin").password("admin")
   					.roles("ADMIN", "USER").and().withUser("user").password("user")
   					.roles("USER");
   		}

   	}

}