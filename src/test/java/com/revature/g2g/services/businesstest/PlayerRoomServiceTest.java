package com.revature.g2g.services.businesstest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.revature.g2g.models.Game;
import com.revature.g2g.models.Player;
import com.revature.g2g.models.Room;
import com.revature.g2g.models.SkillPlayerJT;
import com.revature.g2g.repositories.IPlayerDAO;
import com.revature.g2g.repositories.PlayerDAO;
import com.revature.g2g.repositories.RoomDAO;
import com.revature.g2g.repositories.SkillPlayerJTDAO;
import com.revature.g2g.services.business.PlayerRoomService;
import com.revature.g2g.services.handlers.PlayerHandler;
import com.revature.g2g.services.handlers.RoomHandler;

@RunWith(MockitoJUnitRunner.class)
public class PlayerRoomServiceTest {
	
	@Mock
	PlayerRoomService service;
	@InjectMocks
	RoomHandler roomHandler;
	@InjectMocks
	Player player;
	@InjectMocks
	Room room;
	@InjectMocks
	Game game;
	@InjectMocks
	PlayerDAO pdao;
	@InjectMocks
	RoomDAO rdao;
	@InjectMocks
	SkillPlayerJTDAO spdao;
	@InjectMocks
	PlayerHandler handler;
	
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
	public void testCheckQualiFiedRoomsTrue() {
		Set<SkillPlayerJT> set = new HashSet<>();
		SkillPlayerJT[] array = new SkillPlayerJT[set.size()];
		when(service.checkQualfiedRoom(player, room, array))
			.thenReturn(true);
		boolean result = service.checkQualfiedRoom(player, room, array);
		verify(service).checkQualfiedRoom(player, room, array);
		assertTrue(result);
	}
	
	@Test
	public void testCheckQualiFiedRoomsFalse() {
		Set<SkillPlayerJT> set = new HashSet<>();
		SkillPlayerJT[] array = new SkillPlayerJT[set.size()];
		when(service.checkQualfiedRoom(player, room, array))
			.thenReturn(false);
		boolean result = service.checkQualfiedRoom(player, room, array);
		verify(service).checkQualfiedRoom(player, room, array);
		assertFalse(result);
	}
	
	@Test
	public void testGetQualifiedRoomsGame() {
		List<Room> list = new ArrayList<>();
		when(service.getQualifiedRooms(player, game))
			.thenReturn(list);
		List<Room> result = service.getQualifiedRooms(player, game);
		verify(service).getQualifiedRooms(player,game);
		assertEquals(result, list);
	}
	


}
