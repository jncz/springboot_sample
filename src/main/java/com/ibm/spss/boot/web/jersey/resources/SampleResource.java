package com.ibm.spss.boot.web.jersey.resources;

import java.util.Date;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibm.spss.boot.dao.CityRepository;
import com.ibm.spss.boot.domain.City;

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
	
//	@POST
//	@Path("file")
//	@Consumes(MediaType.MULTIPART_FORM_DATA)
//	public @ResponseBody String testUploadFile(
//			@FormDataParam("file") InputStream file,
//			@FormDataParam("file") FormDataContentDisposition fileDisposition) {
//		
//		String name = fileDisposition.getFileName();
//		
//		return "uploaded file name is: "+name;
//	}
	
	@POST
	@Path("f2")
	public @ResponseBody String post(final FormDataMultiPart multiPart) {
		String name = multiPart.getBodyParts().get(0).getContentDisposition().getFileName();
	    return "file name is: "+name;
	}
}
