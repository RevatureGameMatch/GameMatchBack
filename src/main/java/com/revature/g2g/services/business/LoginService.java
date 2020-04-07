package com.revature.g2g.services.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.exceptions.PasswordMatchFailed;
import com.revature.g2g.exceptions.UserNotFound;
import com.revature.g2g.models.Player;
import com.revature.g2g.services.handlers.PlayerHandler;
import com.revature.g2g.services.helpers.LoggerSingleton;
import com.revature.g2g.services.helpers.PasswordHelper;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LoginService {
	@Autowired
	private LoggerSingleton loggerSingleton;
	@Autowired
	private PasswordHelper passwordHelper;
	public Player login(String username, String password) {
		Player result = null;
		Player checkUser = new PlayerHandler().findByUsername(username);
		if (checkUser == null) {
			String messageUser = "Login Failed, invalid user: "+ username;
			loggerSingleton.getAccessLog().warn(messageUser);
			throw new UserNotFound();
		}else if(passwordHelper.checkPassword(password, checkUser.getPlayerPassword())) {
			result = checkUser;
		}else {
			String messagePassword = "Login Failed, wrong password: "+ username;
			loggerSingleton.getAccessLog().warn(messagePassword);
			throw new PasswordMatchFailed();
		}
		return result;
	}
}
