package com.ibm.spss.boot;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.ibm.spss.boot.domain.User;
import com.ibm.spss.boot.service.UserRepository;

public class UserRepositoryIntegrationTests {
	private static ConfigurableApplicationContext context;

	@BeforeClass
	public static void start() throws Exception {
		context = SpringApplication.run(App.class);
	}

	@AfterClass
	public static void stop() {
		if (context != null) {
			context.close();
		}
	}

	@Test
	public void createUser(){
		UserRepository bean = context.getBean(UserRepository.class);
		User user = new User("pli");
		user = bean.save(user );
		
		Assert.assertNotNull(user.getId());
	}
}
