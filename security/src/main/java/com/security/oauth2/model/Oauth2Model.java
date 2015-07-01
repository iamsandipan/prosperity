package com.security.oauth2.model;

import com.google.gson.annotations.SerializedName;

public class Oauth2Model {
	
	@SerializedName("access_token") 
	private String token;
	
	@SerializedName("refresh_token") 
	private String refreshToken;
	
	@SerializedName("expires_in")
	private long ttl;
	
	@SerializedName("token_type") 
	private String type;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public long getTtl() {
		return ttl;
	}
	public void setTtl(long ttl) {
		this.ttl = ttl;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
