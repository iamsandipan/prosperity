package com.data.dao.book;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.book.Event;

@Service
public class EventDAOService {

	@Autowired
	private EntityManager entityManager;

	
	public void createEvent(String type, String context){
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		Timestamp currentTimestamp = new Timestamp(now.getTime());
		Event event = new Event();
		event.setCreationDate(currentTimestamp );
		event.setId(UUID.randomUUID().toString());
		event.setType(type);
		event.setContext(context);
		entityManager.getTransaction().begin();
		entityManager.persist(event);
		entityManager.getTransaction().commit();
	}

}
