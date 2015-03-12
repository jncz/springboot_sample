package com.ibm.spss.boot.web;

import java.io.IOException;

import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.spss.boot.dao.CityRepository;
import com.ibm.spss.boot.domain.City;
import com.ibm.spss.boot.Constants;

@RestController
@RequestMapping(value=Constants.RESTPATH_SPRING_SAMPLE)
public class SprintgRestController {

	@Autowired
	private CityRepository repository;
	
	@Value("${app.name}")
	private String appname;
	
	@RequestMapping(method=RequestMethod.GET)
    public String get_root_path() {
    	return "root path";
    }
	
	@RequestMapping(value="/appname",method=RequestMethod.GET)
    public String get_appname() {
    	return "app name is: "+appname;
    }
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
    public String get_with_sub_path() {
    	return "sub path";
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
    
    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public String handleFormUpload(@RequestParam("filename") String name,@RequestParam("file") Part file) throws IOException {

        return "upload file orginal name is: "+file.getSubmittedFileName()+", new name is: "+name;
    }

}