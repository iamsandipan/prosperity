package com.data.dao.book;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.book.Card;

@Service
public class CardDAOService {

	@Autowired
	private EntityManager entityManager;

	public String createCard(String lessonXml, String previousCardId, String nextCardId) {
		Card lesson = new Card();
		lesson.setId(UUID.randomUUID().toString());
		lesson.setCardText(lessonXml);
		lesson.setPreviousCardId(previousCardId);
		lesson.setNextCardId(nextCardId);
		entityManager.getTransaction().begin();
		entityManager.persist(lesson);
		entityManager.getTransaction().commit();
		return lesson.getId();
	}

	public String updateCard(Card lesson) {
		entityManager.getTransaction().begin();
		entityManager.persist(lesson);
		entityManager.getTransaction().commit();
		return lesson.getId();
	}

	public Card findCardById(String id) {
		Query q = entityManager.createNamedQuery("Card.findCardById", Card.class);
		q.setParameter("id", id);
		return (Card) q.getSingleResult();
	}

	public void deleteCardById(String id) {
		Card lesson = findCardById(id);
		if(lesson == null){
			return;
		}
		entityManager.getTransaction().begin();
		entityManager.remove(lesson);
		entityManager.getTransaction().commit();
	}

}
