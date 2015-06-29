package com.prosperity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.book.Item;
import com.data.dao.book.EventDAOService;
import com.data.dao.book.ItemDAOService;
import com.data.dao.book.CardDAOService;

@Service
public class ItemWebLayerServices {
	
	@Autowired
	private ItemDAOService itemDAOService;
	
	@Autowired
	private EventDAOService eventDAOService;
	
	@Autowired
	private CardDAOService lessonDAOService;
	
	
	
	public void createItem(Item item){
		itemDAOService.createItem(item);
	}
	
	public void createEvent(String type, String context){
		eventDAOService.createEvent(type, context);
	}
	
	public void createLesson(String type, String context){
		eventDAOService.createEvent(type, context);
	}

}
