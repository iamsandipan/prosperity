package com.data.book;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the item database table.
 * 
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Item.findById", query="SELECT i FROM Item i where i.id= :id"),
    @NamedQuery(name="Item.findByUserIdentity", query="SELECT i FROM Item i where i.useridentity= :useridentity"),
    @NamedQuery(name="Item.findByUserIdentityAndMerchant", query="SELECT i FROM Item i where i.useridentity= :useridentity and i.merchant = :merchant and i.fulfilled = :fulfilled"),
}) 
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="creation_date")
	private Timestamp creationDate;

	@Column(name="merchant")
	private String merchant;

	@Column(name="modified_date")
	private Timestamp modifiedDate;

	@Column(name="useridentity")
	private String useridentity;

	@Column(name="fulfilled")
	private boolean fulfilled;
	
	public Item() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public String getMerchant() {
		return this.merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getUseridentity() {
		return this.useridentity;
	}

	public void setUseridentity(String useridentity) {
		this.useridentity = useridentity;
	}

	public boolean isFulfilled() {
		return fulfilled;
	}

	public void setFulfilled(boolean fulfilled) {
		this.fulfilled = fulfilled;
	}

}