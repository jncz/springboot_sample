package com.ibm.spss.boot;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.spss.boot.domain.User;
import com.ibm.spss.boot.service.UserManagementService;

public class UserManagementServiceTest extends ServiceBaseTest {

	@Autowired
	UserManagementService service;

	@Test
	public void testAddRoleToUser() {
		String name = "pli";
		String state = "valid";
		long uid = service.addUser(name, state);

		long rid = service.addRole(name);
		long[] rids = new long[] { rid };
		boolean result = service.addRoleToUser(uid, rids);

		Assert.assertTrue(result);

		User user = service.getUser(uid);

		Assert.assertTrue(user.getRoles().size() == 1);
	}

	@Test
	public void testAddRoleToUserWithInvalidRoleId() {
		String name = "pli";
		String state = "valid";
		long uid = service.addUser(name, state);

		long rid = 10010;
		long[] rids = new long[] { rid };
		boolean result = service.addRoleToUser(uid, rids);

		Assert.assertTrue(result);

		User user = service.getUser(uid);

		Assert.assertTrue(user.getRoles().size() == 0);
	}
}
