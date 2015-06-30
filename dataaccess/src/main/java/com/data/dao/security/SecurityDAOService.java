package com.data.dao.security;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.security.OauthCred;

@Service
public class SecurityDAOService {

	@Autowired
	private EntityManager entityManager;

	public OauthCred saveToken(OauthCred authCred) {
		entityManager.getTransaction().begin();
		entityManager.persist(authCred);
		entityManager.getTransaction().commit();
		return authCred;
	}
	
	public OauthCred findOauthCredById(String id){
		Query q = entityManager.createNamedQuery("OauthCred.findCardById", OauthCred.class);
		q.setParameter("id", id);
		return (OauthCred) q.getSingleResult();
	}
	
	public OauthCred refreshToken(String id, String newToken){
		OauthCred oauthCred = findOauthCredById(id);
		oauthCred.setOauthToken(newToken);
		entityManager.getTransaction().begin();
		entityManager.persist(oauthCred);
		entityManager.getTransaction().commit();
		return oauthCred;
	}

}
