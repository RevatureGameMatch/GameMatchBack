package com.revature.g2g.services.handlerstest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerRole;
import com.revature.g2g.repositories.IPlayerDAO;
import com.revature.g2g.repositories.PlayerDAO;
import com.revature.g2g.services.handlers.PlayerHandler;

public class PlayerHandlerTest {
	@Mock
	PlayerHandler handler;
	@InjectMocks
	PlayerDAO dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsert() {
		Player p = new Player();
		handler.insert(p);
		verify(handler).insert(p);
	}
	
	@Test
	public void testFindById() {
		Player p = new Player(15, "Kayla", "email.com", "password", PlayerRole.ADMIN);
		when(handler.findById(15)).
			thenReturn(p);
		Player actual = handler.findById(15);
		verify(handler).findById(15);
		assertEquals("Kayla", actual.getPlayerUsername());
		assertEquals("password", actual.getPlayerPassword());
		assertEquals("email.com", actual.getPlayerEmail());
	}
	
	@Test
	public void testFindByUsername() {
		Player p = new Player(15, "Kayla", "email.com", "password", PlayerRole.ADMIN);
		when(handler.findByUsername("Kayla")).
			thenReturn(p);
		Player actual = handler.findByUsername("Kayla");
		verify(handler).findByUsername("Kayla");
		assertEquals("Kayla", actual.getPlayerUsername());
		assertEquals("password", actual.getPlayerPassword());
		assertEquals("email.com", actual.getPlayerEmail());
	}
	
	@Test
	public void testFindByEmail() {
		Player p = new Player(15, "Kayla", "email.com", "password", PlayerRole.ADMIN);
		when(handler.findByEmail("email.com")).
			thenReturn(p);
		Player actual = handler.findByEmail("email.com");
		verify(handler).findByEmail("email.com");
		assertEquals("Kayla", actual.getPlayerUsername());
		assertEquals("password", actual.getPlayerPassword());
		assertEquals("email.com", actual.getPlayerEmail());
	}
	
	@Test
	public void testFindAll() {
		
		Player p = new Player(15, "Kayla", "email.com", "password", PlayerRole.ADMIN);
		when(handler.findByEmail("email.com")).
			thenReturn(p);
		Player actual = handler.findByEmail("email.com");
		verify(handler).findByEmail("email.com");
		assertEquals("Kayla", actual.getPlayerUsername());
		assertEquals("password", actual.getPlayerPassword());
		assertEquals("email.com", actual.getPlayerEmail());
	}
	

}
