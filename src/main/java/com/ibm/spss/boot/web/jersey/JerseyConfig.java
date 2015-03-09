package com.ibm.spss.boot.web.jersey;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.ibm.spss.boot.web.jersey.resources.SampleResource;
import com.ibm.spss.boot.web.jersey.resources.UserRoleResource;

@Component
@ApplicationPath("/jersey")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(SampleResource.class);
        register(UserRoleResource.class);
    }

}