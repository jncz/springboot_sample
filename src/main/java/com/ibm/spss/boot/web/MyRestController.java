package com.ibm.spss.boot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.spss.boot.domain.City;
import com.ibm.spss.boot.service.CityRepository;

@RestController
@RequestMapping(value="/city")
public class MyRestController {

	@Autowired
	private CityRepository repository;
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
    public String test() {
    	return "working";
    }
	
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public City getUser(@PathVariable Long id) {
    	return repository.findOne(id);
    }

    @RequestMapping(method=RequestMethod.PUT)
    public City createCity(@RequestParam String name, @RequestParam String state) {
    	City s = new City(name,state);
		s = repository.save(s);
		return s;
    }

}