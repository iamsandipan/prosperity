package com.data.dao.book;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.book.Card;
import com.data.book.Lesson;

@Service
public class LessonDAOService {
	
	@Autowired
	private EntityManager entityManager;

	public String createLesson(String lessonMeta){
			Lesson lesson = new Lesson();
			lesson.setId(UUID.randomUUID().toString());
			lesson.setLessonMeta(lessonMeta);
			entityManager.getTransaction().begin();
			entityManager.persist(lesson);
			entityManager.getTransaction().commit();
			return lesson.getId();
	}
	
	public String updateLesson(Lesson newLesson, String id){
		Lesson lesson = findByLessonId(id);
		lesson.setLessonMeta(newLesson.getLessonMeta());
		entityManager.getTransaction().begin();
		entityManager.persist(lesson);
		entityManager.getTransaction().commit();
		return lesson.getId();
}
	
	
	public Lesson findByLessonId(String id){
		Query q = entityManager.createNamedQuery("Lesson.findByLessonId", Lesson.class);
		q.setParameter("id", id);
		return (Lesson) q.getSingleResult();
	}
	
	
}
