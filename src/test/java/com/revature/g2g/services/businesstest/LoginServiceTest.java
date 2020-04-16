package com.revature.g2g.services.businesstest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.g2g.exceptions.PasswordMatchFailed;
import com.revature.g2g.models.Player;
import com.revature.g2g.repositories.IPlayerDAO;
import com.revature.g2g.services.business.LoginService;
import com.revature.g2g.services.handlers.PlayerHandler;

public class LoginServiceTest {

	private LoginService service;
	//private IPlayerDAO dao;
	private PlayerHandler handler;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		service = mock(LoginService.class);
		handler = mock(PlayerHandler.class);
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void testLoginValid() {
//		Player p = new Player();
//		when(service.login("Kayla", "password"))
//			.thenReturn(p);
//		Player expected = handler.findByUsername("Kayla");
//		verify(service).login("Kayla", "password");
//		assertEquals(p, expected);
//			
//	}
	
//	@Test
//	public void testLoginInvalid() {
//		
//		doThrow(new PasswordMatchFailed())
//			.when(service.login("Kayla", ""));
////		then(new PasswordMatchFailed())
////			.isInstanceOf(PasswordMatchFailed.class);
//			
//	}
	

}
