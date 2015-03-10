package com.ibm.spss.boot.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ibm.spss.boot.domain.Role;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long>{}
