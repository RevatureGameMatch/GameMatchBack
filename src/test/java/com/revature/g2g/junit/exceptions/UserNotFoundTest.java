package com.revature.g2g.junit.exceptions;

import org.junit.Test;

import com.revature.g2g.exceptions.UserNotFound;

public class UserNotFoundTest {

	@Test(expected = UserNotFound.class)
	public void testNoArgs() {
		throw new UserNotFound();
	}
	@Test(expected = UserNotFound.class)
	public void testSelf() {
		Exception e = new UserNotFound();
		throw new UserNotFound(e);
	}
	@Test(expected = UserNotFound.class)
	public void testString() {
		throw new UserNotFound("Say Something");
	}
	@Test(expected = UserNotFound.class)
	public void testStringSelf() {
		Exception e = new UserNotFound();
		throw new UserNotFound("Say Something", e);
	}
	@Test(expected = UserNotFound.class)
	public void testComplex() {
		Exception e = new UserNotFound();
		throw new UserNotFound("Say Something", e, true, true);
	}

}
