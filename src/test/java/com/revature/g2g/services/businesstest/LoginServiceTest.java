package com.revature.g2g.services.businesstest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.revature.g2g.exceptions.PasswordMatchFailed;
import com.revature.g2g.exceptions.UserNotFound;
import com.revature.g2g.models.Player;
import com.revature.g2g.services.business.LoginService;
import com.revature.g2g.services.handlers.PlayerHandler;

public class LoginServiceTest {

	
	private LoginService service;
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

	@Test
	public void testLoginValid() {
		Player p = service.login("Kayla", "password");
		Player expected = handler.findByPlayerUsername("Kayla");
		verify(service).login("Kayla", "password");
		assertEquals(p, expected);
			
	}
	
	@Test(expected = PasswordMatchFailed.class)
	public void testLoginInvalidPassword() {
		when(service.login("Kayla", "pw"))
			.thenThrow(new PasswordMatchFailed());
		service.login("Kayla", "pw");
			
	}
	
	@Test(expected = UserNotFound.class)
	public void testLoginInvalidUser() {
		when(service.login("Kaylalala", "pw"))
			.thenThrow(new UserNotFound());
		service.login("Kaylalala", "pw");
			
	}
	

}
