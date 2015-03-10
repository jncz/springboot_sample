package com.ibm.spss.boot.web.jersey.resources;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibm.spss.boot.dao.RoleRepository;
import com.ibm.spss.boot.dao.UserRepository;
import com.ibm.spss.boot.domain.Role;
import com.ibm.spss.boot.domain.User;

@Path("/user")
public class UserRoleResource {
	@Autowired
	private DataSource ds;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RoleRepository rolerepository;

	@GET
	public String home() {
		return "This is user management";
	}
	
	@GET
	@Path("{id}")
	public String getUser(@PathParam("id") long id){
		User user = repository.findOne(id);
		
		return user.toString();
	}
	
	@PUT
	@Path("/role")
	public @ResponseBody String saveRole(@FormParam("name") String name){
		Role role = new Role(name);
		Role rrole = rolerepository.save(role );
		
		return ""+rrole.getId();
	}
	
	@PUT
	public @ResponseBody String saveUser(@FormParam("name") String name){
		User user = new User(name);
		User ruser = repository.save(user );
		
		return ""+ruser.getId();
	}
	
	@POST
	public @ResponseBody String addRoleToUser(@FormParam("uid") long id,@FormParam("rid") long rid){
		User user = repository.findOne(id);
		Role role = rolerepository.findOne(rid);
		Set<Role> roles = user.getRoles();
		if(roles != null){
			if(!roles.contains(role)){
				roles.add(role);
			}
		}else{
			roles = new HashSet<Role>();
			roles.add(role);
			user.setRoles(roles);
		}
		User ruser = repository.save(user);
		return ""+ruser.getId();
	}
}
