package com.ibm.spss.boot.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ibm.spss.boot.domain.City;

public interface CityRepository extends PagingAndSortingRepository<City, Long> {

}
