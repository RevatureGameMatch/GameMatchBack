package com.revature.g2g.services.jda.helpers;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.g2g.models.Game;
import com.revature.g2g.services.helpers.GameHelper;

public class GameHelperTest {
	private static GameHelper gameHelper;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		gameHelper = new GameHelper();
	}

	@Test
	public void test() {
		Game g = new Game();
		g.setDescription("<script>alert('evil code deployed!')</script>With a description.");
		g.setLink("<script>alert('evil code deployed!')</script>With a link.");
		g.setName("<script>alert('evil code deployed!')</script>With a name.");
		Game clean = gameHelper.clean(g);
		assertEquals("With a description.", clean.getDescription());
		assertEquals("With a link.", clean.getLink());
		assertEquals("With a name.", clean.getName());
	}
}
