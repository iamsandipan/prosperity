package com.data.dao.book;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.data.book.Card;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")

public class LessonDAOServiceTest {

	@Autowired
	private CardDAOService lessonDAO;
	private String id;
	
/*	@Before
	public void setUp() throws Exception {
		id = lessonDAO.("<xml></xml>");
	}

	
	@After
	public void tearDown() throws Exception {
		lessonDAO.deleteLessonById(id);
	}

	@Test
	public void testFindLesson() {
		Card lesson = lessonDAO.findLessonById(id);
		assertEquals(lesson.getLessonText(), "<xml></xml>");
	}
	
	@Test
	public void testUpdateLesson() {
		Card lesson = lessonDAO.findLessonById(id);
		lesson.setLessonText("<xml>aa</xml>");
		lessonDAO.updateLesson(lesson);
		Card lessonAfterUpd = lessonDAO.findLessonById(lesson.getId());
		assertEquals("<xml>aa</xml>", lessonAfterUpd.getLessonText());
	}
*/
}
