package com.revature.g2g.junit.exceptions;

import org.junit.Test;

import com.revature.g2g.exceptions.NullGuildException;

public class NullGuildExceptionTest {

	@Test(expected = NullGuildException.class)
	public void testNoArgs() {
		throw new NullGuildException();
	}
	@Test(expected = NullGuildException.class)
	public void testSelf() {
		Exception e = new NullGuildException();
		throw new NullGuildException(e);
	}
	@Test(expected = NullGuildException.class)
	public void testString() {
		throw new NullGuildException("Say Something");
	}
	@Test(expected = NullGuildException.class)
	public void testStringSelf() {
		Exception e = new NullGuildException();
		throw new NullGuildException("Say Something", e);
	}
	@Test(expected = NullGuildException.class)
	public void testComplex() {
		Exception e = new NullGuildException();
		throw new NullGuildException("Say Something", e, true, true);
	}

}
