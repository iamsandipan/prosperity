package com.data.export;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.data.dao.book.CardDAOService;
import com.data.dao.book.EventDAOService;
import com.data.dao.book.LessonDAOService;
import com.data.dao.book.UserDAOService;
import com.data.dao.entity.EntityManagerService;

@Configuration
@Import({EntityManagerService.class, MongoDbConfiguration.class, PropertiesConfiguration.class})
public class BookExportConfig {
	

	@Bean
	public UserDAOService getUserDAOService(){
		return new UserDAOService();
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
