package com.data.export;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.data.dao.book.CardDAOService;
import com.data.dao.book.EventDAOService;
import com.data.dao.book.ItemDAOService;
import com.data.dao.book.LessonDAOService;
import com.data.dao.entity.EntityManagerService;
import com.data.dao.security.SecurityDAOService;

@Configuration
@Import({EntityManagerService.class})
public class BookExportConfig {
	
	

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

	@Bean
	public SecurityDAOService getSecurityDAOService(){
		return new SecurityDAOService();
	}

}
