package com.data.dao.book;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.data.book.Item;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class BookingDAOServiceTest {

	private Item item ;

	@Autowired
	private ItemDAOService itemDAO;
	
	@Before
	public void setUp() throws Exception {
		item = new Item();
		item.setMerchant("aaa");
		item.setUseridentity("aaaaaaa");
		itemDAO.createItem(item);
	}

	@After
	public void tearDown() throws Exception {
		itemDAO.deleteItem(item.getId());
	}

	@Test
	public void testGetByPrimaryKey() {
		assertNotNull(item.getId());
		Item founditem = itemDAO.findItemById(item.getId());
		assertEquals(item.getId(), founditem.getId());
	}

	@Test
	public void testUpdateItem() {
		Item founditem = itemDAO.findItemById(item.getId());
		founditem.setUseridentity("userid");
		founditem.setMerchant("merchant");
		founditem.setFulfilled(true);
		itemDAO.updateItem(founditem);
		Item founditemAfterUpdate = itemDAO.findItemById(founditem.getId());
		
		assertEquals(founditemAfterUpdate.getUseridentity(), "userid");
		assertEquals(founditemAfterUpdate.getMerchant(), "merchant");
		assertTrue(founditemAfterUpdate.isFulfilled());
		
	}
	
	/*
	@Test
	public void testFindAllItemsByUserIdentity() {
		List<Item> items = itemDAO.findAllItemsByUserIdentity(item.getUseridentity());
		assertTrue(items.size() > 1);
	}

	@Test
	public void testFindAllItemsByUserIdentityAndMerchantAndFulfilledStatus() {
		List<Item> items = itemDAO.findAllItemsByUserIdentityAndMerchantAndFulfilledStatus(item.getUseridentity(), item.getMerchant(), item.isFulfilled());
		assertTrue(items.size() > 1);
	}

*/	
	
}
