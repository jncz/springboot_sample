package com.ibm.spss.boot.service;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ibm.spss.boot.domain.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	@Modifying
	@Transactional
	@Query("delete from User u where u.active = false")
	void deleteInactiveUsers();
}
