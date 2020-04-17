package com.revature.g2g.services.handlerstest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.g2g.models.Game;
import com.revature.g2g.models.SkillGameJT;
import com.revature.g2g.repositories.IGameDAO;
import com.revature.g2g.services.handlers.GameHandler;

public class GameHandlerTest {

	@InjectMocks
	GameHandler handler;
	@Mock
	IGameDAO dao;
	
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
		Game g = new Game();
		handler.insert(g);
		verify(dao).insert(g);
	}
	
	@Test
	public void testFindById() {
		Set<SkillGameJT> set = new HashSet<>();
		Game g = new Game(1, "name","link.com", "description", 2, set);
		when(dao.findById(1))
			.thenReturn(g);
		Game actual = handler.findById(1);
		verify(dao).findById(1);
		assertEquals(actual, g);
		
	}
	
	@Test
	public void testFindByName() {
		Set<SkillGameJT> set = new HashSet<>();
		Game g = new Game(1, "name","link.com", "description", 2, set);
		when(dao.findByName("name"))
			.thenReturn(g);
		Game actual = handler.findByName("name");
		verify(dao).findByName("name");
		assertEquals(actual, g);
	}
	
	@Test
	public void testFindAll() {
		Set<SkillGameJT> set = new HashSet<>();
		Game g = new Game(1, "name","link.com", "description", 2, set);
		Game g2 = new Game(3, "hi","hi.com", "words", 4, set);
		Set<Game> gameSet = new HashSet<>();
		gameSet.add(g);
		gameSet.add(g2);
		when(dao.findAll())
			.thenReturn(gameSet);
		Set<Game> actual = handler.findAll();
		verify(dao).findAll();
		assertEquals(2, actual.size());
		
		
	}
	
	@Test
	public void testUpdate() {
		Game g = new Game();
		handler.update(g);
		verify(dao).update(g);
	}
	
	@Test
	public void testDelete() {
		Game g = new Game();
		handler.delete(g);
		verify(dao).delete(g);
	}

}
