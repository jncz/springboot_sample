package com.ibm.spss.boot.web;

import java.io.IOException;

import javax.servlet.http.Part;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.spss.boot.Constants;
import com.ibm.spss.boot.cache.Cache;
import com.ibm.spss.boot.dao.CityRepository;
import com.ibm.spss.boot.dao.ElasticUserRepository;
import com.ibm.spss.boot.domain.City;
import com.ibm.spss.boot.domain.DocUser;
import com.ibm.spss.boot.mail.Mail;

@RestController
@RequestMapping(value=Constants.RESTPATH_SPRING_SAMPLE)
public class SprintgRestController {

	private Log log = LogFactory.getLog(SprintgRestController.class);
	
	@Autowired
	private CityRepository repository;
	
	@Autowired
	private ElasticUserRepository elaURepo;
	
	@Autowired
	private Cache cache;
	
	@Value("${app.name}")
	private String appname;
	
	@RequestMapping(method=RequestMethod.GET)
    public String get_root_path() {
    	return "root path";
    }
	
	@RequestMapping(value="/log",method=RequestMethod.GET)
    public String test_log() {
		log.info("This is a test log");
    	return log == null?"no log":log.toString();
    }
	
	@RequestMapping(value="/appname",method=RequestMethod.GET)
    public String get_appname() {
    	return "app name is: "+appname;
    }
	
	@RequestMapping(value="/elastic",method=RequestMethod.GET)
    public DocUser get_user_by_elastic(@RequestParam Long id) {
		DocUser user = elaURepo.findOne(id);
    	return user;
    }
	
	@RequestMapping(value="/elastic",method=RequestMethod.PUT)
    public DocUser save_user_by_elastic(@RequestParam String name,@RequestParam Long id) {
		DocUser user = new DocUser(name);
		user.setId(id);
		user = elaURepo.save(user);
    	return user;
    }
	
	@RequestMapping(value="/cache",method=RequestMethod.PUT)
    public void put_to_cache(@RequestParam String key,@RequestParam String value) {
		cache.put(key, value);
    }
	
	@RequestMapping(value="/cache",method=RequestMethod.GET)
    public String get_from_cache(@RequestParam String key) {
		return cache.get(key);
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
    
    @Autowired
    private Mail mail;
    
    @RequestMapping(value="mail",method=RequestMethod.POST)
    public String send_mail(@RequestParam String to, @RequestParam String subject, @RequestParam String msg){
    	mail.send(to,subject,msg);
    	return "mail send";
    }

}