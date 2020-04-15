package com.revature.g2g.junit.services.helpers;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.revature.g2g.services.helpers.BalanceConstants;

public class BalanceConstantsTest {

	@Test
	public void testMinValue() {
		assertTrue(BalanceConstants.getMinValue() < BalanceConstants.getMaxValue());
	}
	@Test
	public void testRangeValue() {
		assertTrue((BalanceConstants.getMaxValue() - BalanceConstants.getMinValue()) > 50);
	}
	@Test
	public void testStartValue() {
		assertTrue(BalanceConstants.getStartValue() < BalanceConstants.getMaxValue());
		assertTrue(BalanceConstants.getStartValue() > BalanceConstants.getMinValue());
	}
	@Test
	public void testGain() {
		assertTrue(BalanceConstants.getMaxGain() > 0);
	}
	@Test
	public void testLoss() {
		assertTrue(BalanceConstants.getMaxLoss() < 0);
	}
	@Test
	public void testExpertise() {
		assertTrue(BalanceConstants.getMaxExpertise() > 0);
	}
	@Test
	public void testExpertiseMin() {
		assertTrue(BalanceConstants.getMinExpertise() > 0);
	}
	@Test
	public void testDefaultGameTime() {
		assertTrue(BalanceConstants.getDefaultGameTime() > 600_000L);
		assertTrue(BalanceConstants.getDefaultGameTime() < 10_000_000);
	}
}