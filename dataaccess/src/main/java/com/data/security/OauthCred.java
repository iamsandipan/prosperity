package com.data.security;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the oauth_cred database table.
 * 
 */
@Entity
@Table(name="oauth_cred")
@NamedQueries({
	@NamedQuery(name="OauthCred.findCardById", query="SELECT i FROM OauthCred i where i.id = :id")
}) 

public class OauthCred implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="create_time")
	private Time createTime;

	@Id
	private String id;

	@Column(name="oauth_token")
	private String oauthToken;

	@Column(name="refresh_time")
	private Time refreshTime;

	public OauthCred() {
	}

	public Time getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Time createTime) {
		this.createTime = createTime;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOauthToken() {
		return this.oauthToken;
	}

	public void setOauthToken(String oauthToken) {
		this.oauthToken = oauthToken;
	}

	public Time getRefreshTime() {
		return this.refreshTime;
	}

	public void setRefreshTime(Time refreshTime) {
		this.refreshTime = refreshTime;
	}

}