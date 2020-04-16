package com.revature.g2g.junit.services.helpers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.g2g.models.SkillPlayerJT;
import com.revature.g2g.services.helpers.BalanceConstants;
import com.revature.g2g.services.helpers.BalanceHelper;

public class BalanceHelperTest {
	private static BalanceHelper balanceHelper;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		balanceHelper = new BalanceHelper();
	}

	@Test
	public void testLimitChangeHigh() {
		float value = 1_000_000f;
		assertEquals(BalanceConstants.getMaxGain(), balanceHelper.limitChange(value), 0.000001);
	}
	@Test
	public void testLimitChangeLow() {
		float value = -1_000_000f;
		assertEquals(BalanceConstants.getMaxLoss(), balanceHelper.limitChange(value), 0.000001);
	}
	@Test
	public void testLimitChangeGood() {
		float value = BalanceConstants.getMaxGain()/2;
		assertEquals(value, balanceHelper.limitChange(value), 0.000001);
	}
	@Test
	public void testValueChangeHigh() {
		float value = 1_000_000f;
		assertEquals(BalanceConstants.getMaxValue(), balanceHelper.limitValue(value), 0.000001);
	}
	@Test
	public void testValueChangeLow() {
		float value = -1_000_000f;
		assertEquals(BalanceConstants.getMinValue(), balanceHelper.limitValue(value), 0.000001);
	}
	@Test
	public void testValueChangeGood() {
		float value = BalanceConstants.getMaxValue()/2;
		assertEquals(value, balanceHelper.limitValue(value), 0.000001);
	}
	@Test
	public void testCalculateExpertise() {
		SkillPlayerJT jt = new SkillPlayerJT();
		jt.setValue(BalanceConstants.getStartValue());
		float expertise = balanceHelper.calculateExpertise(jt);
		assertTrue(expertise > BalanceConstants.getMinExpertise());
		assertTrue(expertise < BalanceConstants.getMaxExpertise());
	}
}