package com.data.security.export;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.data.security.SecurityService;

@Configuration
public class SecurityExport {

	@Bean
	public SecurityService getSecurityService(){
		return new SecurityService();
	}

}
