package com.revature.g2g.junit.services.helpers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.g2g.services.helpers.PasswordHelper;

public class PasswordHelperTest {
	private static PasswordHelper passwordHelper;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		passwordHelper = new PasswordHelper();
	}

	@Test
	public void testSimple() {
		String password = "password";
		String hash = passwordHelper.encryptPassword(password);
		assertTrue(passwordHelper.checkPassword(password, hash));
		assertFalse(passwordHelper.checkPassword(password.toUpperCase(), hash));
	}
	@Test
	public void testAlphaNumberic() {
		String password = "pAs1sWo3rD";
		String hash = passwordHelper.encryptPassword(password);
		assertTrue(passwordHelper.checkPassword(password, hash));
		assertFalse(passwordHelper.checkPassword(password.toLowerCase(), hash));
	}
	@Test
	public void testSigns() {
		String password = "p3as$swO9rd";
		String hash = passwordHelper.encryptPassword(password);
		assertTrue(passwordHelper.checkPassword(password, hash));
		assertFalse(passwordHelper.checkPassword(password.toUpperCase(), hash));
	}
	@Test
	public void testNumeric() {
		String password = "167145932";
		String hash = passwordHelper.encryptPassword(password);
		assertTrue(passwordHelper.checkPassword(password, hash));
		String wrongPass = (new Long(password) - 159) + "";
		assertFalse(passwordHelper.checkPassword(wrongPass, hash));
	}
}
