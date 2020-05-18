package com.revature.g2g.services.businesstest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillPlayerJT;
import com.revature.g2g.services.business.SkillPlayerJTService;
import com.revature.g2g.services.handlers.SkillPlayerJTHandler;
import com.revature.g2g.services.helpers.BalanceConstants;
import com.revature.g2g.services.helpers.BalanceHelper;

public class SkillPlayerJTServiceTest {
	
	@Mock
	SkillPlayerJTService service;
	@InjectMocks
	BalanceHelper balanceHelper;
	@InjectMocks
	SkillPlayerJTHandler skillPlayerJTHandler;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMakeDefault() {
		Skill skill = new Skill();
		Player player = new Player();
		SkillPlayerJT spJT = new SkillPlayerJT();
		when(service.addSkillWithDefaultValue(skill, player))
			.thenReturn(spJT);
		SkillPlayerJT result = service.addSkillWithDefaultValue(skill, player);
		result.setExpertise(balanceHelper.calculateExpertise(result));
		SkillPlayerJT expected = new SkillPlayerJT();
		expected.setPlayer(player);
		expected.setSkill(skill);
		expected.setValue(BalanceConstants.getStartValue());
		expected.setExpertise(balanceHelper.calculateExpertise(expected));
		verify(service).addSkillWithDefaultValue(skill,player);
		assertEquals(result, expected);
		
		
	}

}
