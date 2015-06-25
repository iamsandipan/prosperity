package com.security.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;


@Path("/securityservice")
@Component
public class SecurityResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getVersion")
    public String getVersion() {
		return "1.0.0-Beta";
    }


}
