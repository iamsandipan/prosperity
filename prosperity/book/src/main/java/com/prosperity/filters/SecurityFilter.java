package com.prosperity.filters;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.data.security.SecurityService;
import com.security.oauth2.model.Oauth2Model;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

@Component
public class SecurityFilter implements ContainerRequestFilter {

	@Autowired
	private SecurityService securityService;


	@Override
	public ContainerRequest filter(ContainerRequest request) {
		Oauth2Model oauthModel = securityService.getCode();
		
		if(StringUtils.isEmpty(oauthModel.getToken())){
			throw new WebApplicationException (Status.FORBIDDEN);
		}
		return request;
	}

}
