package com.data.security;




import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;



@Service
public class SecurityService {

	private static final String SOMESECRET = "somesecret";
	private static final String clientIdd = "my-trusted-client-with-secret";

	public String getCode() {
		
		Client client = Client.create();
		 
 
		//http://localhost:8080/oauth2/oauth/token?grant_type=password&client_id=my-trusted-client-with-secret&client_secret=somesecret&username=user&password=password
		//clientConfig.register(MyClientResponseFilter.class);
		//.register(new AnotherClientFilter());
		WebResource webTarget = client.resource("http://localhost:8080/oauth2/oauth/token")
				.queryParam("grant_type", "password").queryParam("client_id", "my-trusted-client-with-secret").queryParam("client_secret", "somesecret").queryParam("username","user").queryParam("password", "password");
		try {
			return webTarget.path("").accept(MediaType.APPLICATION_JSON).get(String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return "Hello";
	}
}
