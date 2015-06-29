package com.prosperity.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.data.book.Card;
import com.data.dao.book.CardDAOService;
import com.data.dao.security.SecurityDAOService;
import com.data.security.OauthCred;
import com.prosperity.model.CardWebModel;
import com.prosperity.model.OauthWebModel;

@Path("/oauth2")
@Component
public class OAuth2Resource {
	
	
	@Autowired
	private SecurityDAOService securityDAOService;

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
    @Path("/getToken/{id}")
    public OauthWebModel refreshToken(@PathParam("id") String id) {
		//Return the xml formmat of the lesson
		OauthWebModel authModel = new OauthWebModel();
		//Make a call to oauth facebook
		
		OauthCred authCred = new OauthCred();
		//Populate Auth Model and  save to db
		OauthCred card = securityDAOService.saveToken(authCred);

		return authModel;
    }
	


}
