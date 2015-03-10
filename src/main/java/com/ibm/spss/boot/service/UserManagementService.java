package com.ibm.spss.boot.service;

import com.ibm.spss.boot.domain.User;

public interface UserManagementService {

	long addUser(String name,String state);
	boolean addRoleToUser(long uid,long[] rids);
	long addRole(String name);
	User getUser(long uid);
}
