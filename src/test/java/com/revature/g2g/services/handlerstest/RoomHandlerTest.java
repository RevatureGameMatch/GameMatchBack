package com.revature.g2g.services.handlerstest;

import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.g2g.models.Room;
import com.revature.g2g.repositories.IRoomDAO;
import com.revature.g2g.services.handlers.RoomHandler;

public class RoomHandlerTest {
	
	@InjectMocks
	RoomHandler handler;

	@Mock
	IRoomDAO dao;

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

//	@Test
//	public void testInsert() {
//		Room r = new Room();
//		handler.insert(r);
//		verify(dao).insert(r);
//	}
	
//	@Test
//	public void findById() {
//		Game g = new Game();
//		Room r = new Room("name", 3, 10, "description", RoomPlayStyle.CASUAL, g);
//		when(dao.findById(0))
//			.thenReturn(r);
//		Room actual = handler.findById(0);
//		verify(dao).findById(0);
//		assertEquals(actual, r);
//	}
	
//	@Test
//	public void findAll() {
//		Set<Room> set = new HashSet<>();
//		Game g = new Game();
//		Room r = new Room("name", 3, 10, "description", RoomPlayStyle.CASUAL, g);
//		Room r2 = new Room("name2", 4, 11, "des", RoomPlayStyle.HYBRID, g);
//		set.add(r);
//		set.add(r2);
//		when(dao.findAll()).thenReturn(set);
//		Set<Room> actual = handler.findAll();
//		verify(dao).findAll();
//		assertEquals(2, actual.size());
//		
//	}
	
//	@Test
//	public void findAllByStatus() {
//		Game g = new Game();
//		Set<Room> set = new HashSet<>();
//		Room r = new Room((long)12347084, (long)1234891234, (long)12341234, new Date(), "description", RoomStatus.FULL, RoomPlayStyle.CASUAL);
//		Room r2 = new Room((long)12347084, (long)1234891234, (long)12341234, new Date(), "description", RoomStatus.JOINING, RoomPlayStyle.CASUAL);
//		set.add(r);
//		when(dao.findByStatus(RoomStatus.FULL)).thenReturn(set);
//		Set<Room> actual = handler.findByStatus(RoomStatus.FULL);
//		verify(dao).findByStatus(RoomStatus.FULL);
//		assertEquals(1, actual.size());
//	}
	
//	@Test
//	public void findAllByPlayStyle() {
//		Set<Room> set = new HashSet<>();
//		Game g = new Game();
//		Room r = new Room("name", 3, 10, "description", RoomPlayStyle.CASUAL, g);
//		Room r2 = new Room("name2", 4, 11, "des", RoomPlayStyle.HYBRID, g);
//		set.add(r);
//		when(dao.findByPlayStyle(RoomPlayStyle.CASUAL)).thenReturn(set);
//		Set<Room> actual = handler.findByPlayStyle(RoomPlayStyle.CASUAL);
//		verify(dao).findByPlayStyle(RoomPlayStyle.CASUAL);
//		assertEquals(1, actual.size());
//	}
	
	
//	@Test
//	public void findAllByDiscordVoice() {
//		Room r = new Room((long)12347084, (long)1234891234, (long)12341234, new Date(), "description", RoomStatus.FULL, RoomPlayStyle.CASUAL);
//		when(dao.findRoomByDiscordVoice((long)1234891234)).thenReturn(r);
//		Room actual = handler.findRoomByDiscordVoice((long)1234891234);
//		verify(dao).findRoomByDiscordVoice((long)1234891234);
//		assertEquals(r, actual);
//	}
	
//	@Test
//	public void testUpdate() {
//		Room r = new Room();
//		handler.update(r);
//		verify(dao).update(r);
//	}
	
	@Test
	public void testDelete() {
		Room r = new Room();
		handler.delete(r);
		verify(dao).delete(r);
	}
}
