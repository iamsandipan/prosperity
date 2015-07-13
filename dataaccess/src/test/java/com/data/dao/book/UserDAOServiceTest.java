package com.data.dao.book;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.data.mongo.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring-config.xml")
public class UserDAOServiceTest {
	
	@Autowired
	private UserDAOService userDAOService;
	@Test
	public void testFindLesson() {
		
		String emailAddress = "aaas";
		User user = new User();
		user.setEmailAddress(emailAddress);
		String password = "aaasa";
		user.setHashedPassword(password);
		userDAOService.createUser(emailAddress, user, password);
	}

}
