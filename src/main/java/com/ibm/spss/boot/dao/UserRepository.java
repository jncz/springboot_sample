package com.ibm.spss.boot.dao;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.spss.boot.domain.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

}
