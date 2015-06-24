package com.data.spring.dataservice;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Component;

@Component
public class DataManager {
	private EntityManagerFactory factory ;
	private EntityManager entityManager ;
	
	public DataManager(){
		factory = Persistence.createEntityManagerFactory("dataaccess", System.getProperties());
		entityManager = factory.createEntityManager();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	

}
