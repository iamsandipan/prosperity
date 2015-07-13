package com.data.export;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.data.dao.book.CardDAOService;
import com.data.dao.book.EventDAOService;
import com.data.dao.book.ItemDAOService;
import com.data.dao.book.LessonDAOService;
import com.data.dao.book.UserDAOService;
import com.data.dao.entity.EntityManagerService;
import com.data.oauth2.mongodb.UserRepository;

@Configuration
@Import({EntityManagerService.class})
public class BookExportConfig {
	
	@Bean
	public PasswordEncoder getPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserRepository getUserRepository(){
		return new UserRepository();
	}
	
	
	
	@Bean
	public ItemDAOService getItemDAOService(){
		return new ItemDAOService();
	}
	
	@Bean
	public EventDAOService getEventDAOService(){
		return new EventDAOService();
	}
	
	@Bean
	public CardDAOService getCardDAOService(){
		return new CardDAOService();
	}

	@Bean
	public LessonDAOService getLessonDAOService(){
		return new LessonDAOService();
	}
	

}
