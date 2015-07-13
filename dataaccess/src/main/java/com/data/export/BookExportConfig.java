package com.data.export;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.data.dao.book.CardDAOService;
import com.data.dao.book.EventDAOService;
import com.data.dao.book.ItemDAOService;
import com.data.dao.book.LessonDAOService;
import com.data.dao.book.UserDAOService;
import com.data.dao.book.VerificationTokenDAOService;
import com.data.dao.entity.EntityManagerService;
import com.data.oauth2.mongodb.OAuth2AccessTokenRepository;
import com.data.oauth2.mongodb.OAuth2RefreshTokenRepository;
import com.data.oauth2.mongodb.OAuth2RepositoryTokenStore;

@Configuration
@Import({EntityManagerService.class, MongoDbConfiguration.class, PropertiesConfiguration.class})
public class BookExportConfig {
	
	@Autowired 
	private OAuth2AccessTokenRepository oAuth2AccessTokenRepository;
	
	@Autowired
	private OAuth2RefreshTokenRepository oAuth2RefreshTokenRepository;
	
	@Bean
	public UserDAOService getUserDAOService(){
		return new UserDAOService();
	}

	@Bean
	public VerificationTokenDAOService getVerificationTokenDAOService(){
		return new VerificationTokenDAOService();
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
	public ItemDAOService getItemDAOService(){
		return new ItemDAOService();
	}

	@Bean(name="tokenStore")
	public OAuth2RepositoryTokenStore getOAuth2RepositoryTokenStore(){
		return new OAuth2RepositoryTokenStore(oAuth2AccessTokenRepository, oAuth2RefreshTokenRepository);
	}
}
