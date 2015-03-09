package com.ibm.spss.boot.web.jersey.resources;

import java.util.Date;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibm.spss.boot.domain.City;
import com.ibm.spss.boot.service.CityRepository;

@Path("/sample")
public class SampleResource {
	@Value("${name}")
	@NotNull
	private String name;

	@Value("${app.name}")
	private String appname;
	
	@GET
	public String home() {
		return "This is a hello world! cool, current time is: "
				+ (new Date()).toString() + " , The input name is "+name+" , The current app is "+appname;
	}
	@Autowired
	private DataSource ds;
	@GET
	@Path("ds")
	public String testDS(){
		System.out.println("datasource: "+ds);
		return "ds";
	}
	
	
	@Autowired
	private CityRepository repository;
	@GET
	@Path("{id}")
	public String testDAO(@PathParam("id") long id){
		City city = repository.findOne(id);
		
		return city.toString();
	}
	
	@POST
	@Path("dao")
	public @ResponseBody String testSaveByDAO(@FormParam("name") String name, @FormParam("state") String state){
		City city = new City(name,state);
		City a = repository.save(city );
		
		return ""+a.getId();
	}
}
