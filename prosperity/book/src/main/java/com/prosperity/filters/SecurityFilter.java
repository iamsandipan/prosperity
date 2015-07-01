package com.prosperity.filters;


import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;
public class SecurityFilter implements ResourceFilter, ContainerRequestFilter {
 
	
    public void filter(ContainerRequestContext requestContext) throws IOException {
 
        
    }

	@Override
	public com.sun.jersey.spi.container.ContainerRequestFilter getRequestFilter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContainerResponseFilter getResponseFilter() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
