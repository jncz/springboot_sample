package com.ibm.spss.boot;

import java.util.Iterator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ibm.spss.boot.domain.City;
import com.ibm.spss.boot.service.CityRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class CityRepositoryIntegrationTests {

    @Autowired
    CityRepository repository;

    int city_num = 5;
    
    @Before
    public void setup(){
    	for(int i=0;i<city_num;i++){
    		City city = new City("name","invalid");
    		repository.save(city);
    	}
    }
    
    @After
    public void tearDown(){
    	repository.deleteAll();
    }
    
    @Test
    public void testSave(){
    	City city = new City("name","invalid");
		city = repository.save(city);
		
		Assert.assertNotNull(city.getId());
		
		long num = repository.count();
		
		Assert.assertEquals(6, num);
    }
    
    @Test
    public void testFindAll(){
    	Iterable<City> ite = repository.findAll();
    	Iterator<City> it = ite.iterator();
    	int i = 0;
    	while(it.hasNext()){
    		it.next();
    		i++;
    	}
    	
    	Assert.assertEquals(city_num, i);
    }
}