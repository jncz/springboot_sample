package com.ibm.spss.boot.service;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ibm.spss.boot.domain.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

}
