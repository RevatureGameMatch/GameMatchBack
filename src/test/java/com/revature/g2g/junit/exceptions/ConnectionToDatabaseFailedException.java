package com.revature.g2g.junit.exceptions;

import org.junit.Test;

import com.revature.g2g.exceptions.ConnectionToDatabaseFailed;

public class ConnectionToDatabaseFailedException {

	@Test(expected = ConnectionToDatabaseFailed.class)
	public void testNoArgs() {
		throw new ConnectionToDatabaseFailed();
	}
	@Test(expected = ConnectionToDatabaseFailed.class)
	public void testSelf() {
		Exception e = new ConnectionToDatabaseFailed();
		throw new ConnectionToDatabaseFailed(e);
	}
	@Test(expected = ConnectionToDatabaseFailed.class)
	public void testString() {
		throw new ConnectionToDatabaseFailed("Say Something");
	}
	@Test(expected = ConnectionToDatabaseFailed.class)
	public void testStringSelf() {
		Exception e = new ConnectionToDatabaseFailed();
		throw new ConnectionToDatabaseFailed("Say Something", e);
	}
	@Test(expected = ConnectionToDatabaseFailed.class)
	public void testComplex() {
		Exception e = new ConnectionToDatabaseFailed();
		throw new ConnectionToDatabaseFailed("Say Something", e, true, true);
	}

}
