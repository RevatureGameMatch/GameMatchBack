package com.revature.g2g.junit.services.helpers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.g2g.services.helpers.DiscordHelper;

public class DiscordHelperTest {
	private static DiscordHelper discordHelper;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		discordHelper = new DiscordHelper();
	}

	@Test
	public void checkAdminCheck() {
		assertTrue(discordHelper.adminCheck("ProNobis#7047"));
		assertTrue(discordHelper.adminCheck("kfilio#6124"));
		assertTrue(discordHelper.adminCheck("shotofthewritten#5186"));
		assertFalse(discordHelper.adminCheck("Malamute#8976"));
	}
	@Test
	public void checkGetRoleVoicePermissions() {
		assertTrue(discordHelper.getRoleVoicePermissions() instanceof Collection);
	}
	@Test
	public void checkGetRoleVoiceBans() {
		assertTrue(discordHelper.getRoleVoiceBans() instanceof Collection);
	}
	@Test
	public void checkGetRoleTextPermissions() {
		assertTrue(discordHelper.getRoleTextPermissions() instanceof Collection);
	}
	@Test
	public void checkGetRoleTextBans() {
		assertTrue(discordHelper.getRoleTextBans() instanceof Collection);
	}
}
