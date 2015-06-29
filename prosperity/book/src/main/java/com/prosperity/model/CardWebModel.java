package com.prosperity.model;

import com.data.book.Card;



public class CardWebModel {
	
	private String cardId;
	private String previousCardId;
	private String nextCardId;
	private String cardContent;
	
	
	public CardWebModel(){};
	
	public CardWebModel(Card card){
		
		cardId = card.getId();
		previousCardId = card.getPreviousCardId();
		cardContent = card.getCardText();
		
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getPreviousCardId() {
		return previousCardId;
	}

	public void setPreviousCardId(String previousCardId) {
		this.previousCardId = previousCardId;
	}

	public String getCardContent() {
		return cardContent;
	}

	public void setCardContent(String cardContent) {
		this.cardContent = cardContent;
	}

	public String getNextCardId() {
		return nextCardId;
	}

	public void setNextCardId(String nextCardId) {
		this.nextCardId = nextCardId;
	}

}
