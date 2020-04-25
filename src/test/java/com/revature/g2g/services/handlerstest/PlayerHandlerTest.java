//package com.revature.g2g.services.handlerstest;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import com.revature.g2g.models.Player;
//import com.revature.g2g.models.PlayerRole;
//import com.revature.g2g.repositories.PlayerDAO;
//import com.revature.g2g.services.handlers.PlayerHandler;
//
//public class PlayerHandlerTest {
//	
//	@InjectMocks
//	PlayerHandler handler;
////	@Mock
////	PlayerDAO dao;
//	
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//	}
//
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//	}
//
//	@Before
//	public void setUp() throws Exception {
//		MockitoAnnotations.initMocks(this);
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}
//
////	@Test
////	public void testInsert() {
////		Player p = new Player();
////		handler.insert(p);
////		verify(dao).insert(p);
////	}
//	
////	@Test
////	public void testFindById() {
////		Player p = new Player(15, "Kayla", "email.com", "password", PlayerRole.ADMIN);
////		when(dao.findById(15)).
////			thenReturn(p);
////		Player actual = handler.findById(15);
////		verify(dao).findById(15);
////		assertEquals("Kayla", actual.getPlayerUsername());
////		assertEquals("password", actual.getPlayerPassword());
////		assertEquals("email.com", actual.getPlayerEmail());
////	}
//	
////	@Test
////	public void testFindByUsername() {
////		Player p = new Player(15, "Kayla", "email.com", "password", PlayerRole.ADMIN);
////		when(dao.findByUsername("Kayla")).
////			thenReturn(p);
////		Player actual = handler.findByPlayerUsername("Kayla");
////		verify(dao).findByUsername("Kayla");
////		assertEquals("Kayla", actual.getPlayerUsername());
////		assertEquals("password", actual.getPlayerPassword());
////		assertEquals("email.com", actual.getPlayerEmail());
////	}
//	
////	@Test
////	public void testFindByEmail() {
////		Player p = new Player(15, "Kayla", "email.com", "password", PlayerRole.ADMIN);
////		when(dao.findByEmail("email.com")).
////			thenReturn(p);
////		Player actual = handler.findByEmail("email.com");
////		verify(dao).findByEmail("email.com");
////		assertEquals("Kayla", actual.getPlayerUsername());
////		assertEquals("password", actual.getPlayerPassword());
////		assertEquals("email.com", actual.getPlayerEmail());
////	}
//	
////	@Test
////	public void testFindAll() {
////		Set<Player> set = new HashSet<>();
////		Player k = new Player(15, "Kayla", "kemail.com", "password", PlayerRole.ADMIN);
////		Player p = new Player(22, "Philip", "pemail.com", "pass", PlayerRole.ADMIN);
////		Player n = new Player(23, "Nancy", "nemail.com", "pw", PlayerRole.ADMIN);
////		set.add(k);
////		set.add(p);
////		set.add(n);
////		when(dao.findAll()).
////			thenReturn(set);
////		Set<Player> actual = handler.findAll();
////		assertEquals(3, actual.size());
////		verify(dao).findAll();
////	}
//	
////	@Test
////	public void testFindByRole() {
////		Set<Player> set = new HashSet<>();
////		Player k = new Player(15, "Kayla", "kemail.com", "password", PlayerRole.ADMIN);
////		Player p = new Player(22, "Philip", "pemail.com", "pass", PlayerRole.ADMIN);
////		Player n = new Player(23, "Nancy", "nemail.com", "pw", PlayerRole.ADMIN);
////		set.add(k);
////		set.add(p);
////		set.add(n);
////		when(dao.findByRole(PlayerRole.ADMIN)).
////			thenReturn(set);
////		Set<Player> actual = handler.findByRole(PlayerRole.ADMIN);
////		verify(dao).findByRole(PlayerRole.ADMIN);
////		assertEquals(3, actual.size());
////	}
//	
////	@Test
////	public void testUpdate() {
////		Player p = new Player();
////		handler.update(p);
////		verify(dao).update(p);
////	}
//	
//	@Test
//	public void testDelete() {
//		Player p = new Player();
//		handler.delete(p);
//		verify(dao).delete(p);
//	}
//	
//
//}
