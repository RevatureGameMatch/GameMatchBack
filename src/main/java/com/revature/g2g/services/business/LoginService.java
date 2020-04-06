package com.revature.g2g.services.business;

import org.springframework.stereotype.Service;

import com.revature.g2g.exceptions.PasswordMatchFailed;
import com.revature.g2g.exceptions.UserNotFound;
import com.revature.g2g.models.Player;
import com.revature.g2g.services.handlers.PlayerHandler;
import com.revature.g2g.services.helpers.LoggerSingleton;
import com.revature.g2g.services.helpers.PasswordHelper;

@Service
public class LoginService {
	private LoginService() {
	}
	public static Player login(String username, String password) {
		Player result = null;
		Player checkUser = new PlayerHandler().findByUsername(username);
		if (checkUser == null) {
			LoggerSingleton.getAccessLog().warn("Login Failed, invalid user: "+ username);
			throw new UserNotFound();
		}else if(PasswordHelper.checkPassword(password, checkUser.getPlayerPassword())) {
			result = checkUser;
		}else {
			LoggerSingleton.getAccessLog().warn("Login Failed, wrong password: "+ username);
			throw new PasswordMatchFailed();
		}
		return result;
	}
}
