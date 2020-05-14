package com.revature.g2g.junit;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.revature.g2g.junit.exceptions.ConnectionToDatabaseFailedException;
import com.revature.g2g.junit.exceptions.PasswordMatchFailedTest;
import com.revature.g2g.junit.exceptions.UserNotFoundTest;
import com.revature.g2g.junit.services.helpers.BalanceConstantsTest;
import com.revature.g2g.junit.services.helpers.BalanceHelperTest;
import com.revature.g2g.junit.services.helpers.DiscordHelperTest;
import com.revature.g2g.junit.services.helpers.LoggerSingletonTest;
import com.revature.g2g.junit.services.helpers.PasswordHelperTest;
import com.revature.g2g.services.jda.helpers.GameHelperTest;

@RunWith(Suite.class)
@SuiteClasses({
	BalanceConstantsTest.class,
	BalanceHelperTest.class,
	DiscordHelperTest.class,
	GameHelperTest.class,
	LoggerSingletonTest.class,
	PasswordHelperTest.class,
	ConnectionToDatabaseFailedException.class,
	PasswordMatchFailedTest.class,
	UserNotFoundTest.class
})
public class AllTests {

}
