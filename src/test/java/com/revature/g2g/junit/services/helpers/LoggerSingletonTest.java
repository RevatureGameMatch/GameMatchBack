package com.revature.g2g.junit.services.helpers;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.g2g.services.helpers.LoggerSingleton;

public class LoggerSingletonTest {
	private static LoggerSingleton loggerSingleton;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		loggerSingleton = new LoggerSingleton();
	}

	@Test
	public void testAccess() {
		assertTrue(loggerSingleton.getAccessLog() instanceof org.apache.logging.log4j.Logger);
		assertTrue(loggerSingleton.getAccessLog() instanceof org.apache.logging.log4j.Logger);
	}
	@Test
	public void testBusiness() {
		assertTrue(loggerSingleton.getBusinessLog() instanceof org.apache.logging.log4j.Logger);
		assertTrue(loggerSingleton.getBusinessLog() instanceof org.apache.logging.log4j.Logger);
	}
	@Test
	public void testDiscord() {
		assertTrue(loggerSingleton.getDiscordLog() instanceof org.apache.logging.log4j.Logger);
		assertTrue(loggerSingleton.getDiscordLog() instanceof org.apache.logging.log4j.Logger);
	}
	@Test
	public void testException() {
		assertTrue(loggerSingleton.getExceptionLogger() instanceof org.apache.logging.log4j.Logger);
		assertTrue(loggerSingleton.getExceptionLogger() instanceof org.apache.logging.log4j.Logger);
	}
	@Test
	public void testPerformance() {
		assertTrue(loggerSingleton.getPerformanceLog() instanceof org.apache.logging.log4j.Logger);
		assertTrue(loggerSingleton.getPerformanceLog() instanceof org.apache.logging.log4j.Logger);
	}
}
