package com.revature.g2g.junit.exceptions;

import org.junit.Test;

import com.revature.g2g.exceptions.PasswordMatchFailed;

public class PasswordMatchFailedTest {

	@Test(expected = PasswordMatchFailed.class)
	public void testNoArgs() {
		throw new PasswordMatchFailed();
	}
	@Test(expected = PasswordMatchFailed.class)
	public void testSelf() {
		Exception e = new PasswordMatchFailed();
		throw new PasswordMatchFailed(e);
	}
	@Test(expected = PasswordMatchFailed.class)
	public void testString() {
		throw new PasswordMatchFailed("Say Something");
	}
	@Test(expected = PasswordMatchFailed.class)
	public void testStringSelf() {
		Exception e = new PasswordMatchFailed();
		throw new PasswordMatchFailed("Say Something", e);
	}
	@Test(expected = PasswordMatchFailed.class)
	public void testComplex() {
		Exception e = new PasswordMatchFailed();
		throw new PasswordMatchFailed("Say Something", e, true, true);
	}

}
