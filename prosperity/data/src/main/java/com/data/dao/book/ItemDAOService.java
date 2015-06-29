package com.data.dao.book;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;





import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.data.book.Item;
@Service
public class ItemDAOService {
	
	@Autowired
	private EntityManager entityManager;


	public Item getByPrimaryKey(String primaryKey){
		return null;
		
	}
	
	public String createItem(Item item){
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		Timestamp currentTimestamp = new Timestamp(now.getTime());
		String uuid = UUID.randomUUID().toString();
		item.setCreationDate(currentTimestamp );
		item.setModifiedDate(currentTimestamp);
		item.setId(uuid);
		item.setFulfilled(false);
		entityManager.getTransaction().begin();
		entityManager.persist(item);
		entityManager.getTransaction().commit();
		return uuid;
	}
	
	public Item findItemById(String id){
		Query q = entityManager.createNamedQuery("Item.findById", Item.class);
		q.setParameter("id", id);
		return (Item) q.getSingleResult();
	}
	
	public List<Item> findAllItemsByUserIdentity(String userIdentiry){
		Query q = entityManager.createNamedQuery("Item.findByUserIdentity");
		q.setParameter("useridentity", userIdentiry);
		List results = q.getResultList();
		return new ArrayList<Item>(results);
	}

	public List<Item> findAllItemsByUserIdentityAndMerchantAndFulfilledStatus(String userIdentiry, String merchant, boolean fulfilled){
		Query q = entityManager.createNamedQuery("Item.findByUserIdentityAndMerchant");
		q.setParameter("useridentity", userIdentiry);
		q.setParameter("merchant", merchant);
		q.setParameter("fulfilled", fulfilled);
		List results = q.getResultList();
		return new ArrayList<Item>(results);
	}
	
	public void updateItem(Item item){
		Item itemDB = findItemById(item.getId());
		item.setId(UUID.randomUUID().toString());
		entityManager.getTransaction().begin();
		itemDB.setUseridentity(item.getUseridentity());
		Timestamp ts = new Timestamp(Calendar.getInstance().getTime().getTime());
		itemDB.setModifiedDate(ts);
		itemDB.setMerchant(item.getMerchant());
		itemDB.setFulfilled(item.isFulfilled());
		entityManager.getTransaction().commit();
	}
	
	public void deleteItem(String itemId){
		Item itemDB = findItemById(itemId);
		if(itemDB == null){
			return;
		}
		entityManager.getTransaction().begin();
		entityManager.remove(itemDB);
		entityManager.getTransaction().commit();
	}

	
}
