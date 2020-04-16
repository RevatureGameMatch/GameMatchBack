package com.revature.g2g.junit;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.revature.g2g.junit.services.helpers.BalanceConstantsTest;
import com.revature.g2g.junit.services.helpers.BalanceHelperTest;

@RunWith(Suite.class)
@SuiteClasses({
	BalanceConstantsTest.class,
	BalanceHelperTest.class
})
public class AllTests {

}
