package com.data.security;




import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.security.oauth2.model.Oauth2Model;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;



public class SecurityService {

	private static final String SOMESECRET = "somesecret";
	private static final String clientIdd = "my-trusted-client-with-secret";

	public Oauth2Model getCode() {
		
		Client client = Client.create();
		WebResource webTarget = client.resource("http://localhost:8080/oauth2/oauth/token")
				.queryParam("grant_type", "password").queryParam("client_id", "my-trusted-client-with-secret").queryParam("client_secret", "somesecret").queryParam("username","user").queryParam("password", "password");
		
		try {
			String str =  webTarget.path("").accept(MediaType.APPLICATION_JSON).get(String.class);
			Gson gson = new Gson();
			return gson.fromJson(str, Oauth2Model.class);
		} catch (Exception e) {
			e.printStackTrace();
			return new Oauth2Model();

		}
	
	}
}
