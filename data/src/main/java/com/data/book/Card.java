package com.data.book;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the lesson database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Card.findCardById", query="SELECT i FROM Card i where i.id = :id")
}) 

public class Card implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="card_text")
	private String cardText;

	@Column(name="prev_card_id")
	private String previousCardId;

	@Column(name="next_card_id")
	private String nextCardId;

	
	public Card() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCardText() {
		return this.cardText;
	}

	public void setCardText(String cardText) {
		this.cardText = cardText;
	}
	
	public String getPreviousCardId() {
		return previousCardId;
	}

	public void setPreviousCardId(String previousCardId) {
		this.previousCardId = previousCardId;
	}

	public String getNextCardId() {
		return nextCardId;
	}

	public void setNextCardId(String nextCardId) {
		this.nextCardId = nextCardId;
	}


}