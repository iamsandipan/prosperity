package com.security.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.data.security.SecurityService;


@Path("/securityservice")
@Component
public class SecurityResource {
	
	
	
	@Autowired
	private SecurityService securityService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getVersion")
    public String getVersion() {
		return "1.0.0-Beta";
    }

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getToken")
    public String getToken() {
		return securityService.getCode();
    }

}
