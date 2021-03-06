package com.ibm.spss.boot;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.spss.boot.dao.RoleRepository;
import com.ibm.spss.boot.dao.UserRepository;
import com.ibm.spss.boot.domain.Role;
import com.ibm.spss.boot.domain.User;

public class UserRepositoryIntegrationTests extends ServiceBaseTest{
	
	@Autowired
	UserRepository repo;
	
	@Autowired
	RoleRepository rolerepo;

	@Test
	public void createUser(){
		User user = new User("pli");
		user = repo.save(user );
		
		Assert.assertNotNull(user.getId());
	}
	
	@Test
	public void createRole(){
		Role r = new Role("admin");
		Role r1 = rolerepo.save(r);
		
		Assert.assertNotNull(r1.getId());
	}
	
	@Test
	public void addRoleToUser(){
		Role r = new Role("admin");
		rolerepo.save(r);
		
		User user = new User("pli");
		user = repo.save(user );
		
		
		Set<Role> roles = new HashSet<Role>();
		roles.add(r);
		user.setRoles(roles);
		repo.save(user);
		
		Assert.assertFalse(user.getRoles().isEmpty());
	}
}
