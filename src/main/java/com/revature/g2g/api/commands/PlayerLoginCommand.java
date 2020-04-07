package com.revature.g2g.api.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import com.revature.g2g.api.FrontCommand;
import com.revature.g2g.exceptions.PasswordMatchFailed;
import com.revature.g2g.exceptions.UserNotFound;
import com.revature.g2g.models.Player;
import com.revature.g2g.services.business.LoginService;
import com.revature.g2g.services.helpers.LoggerSingleton;

public class PlayerLoginCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		if (type.equals("POST")) {
			Player player = om.readValue(body,  Player.class);
			try {
//				player = LoginService.login(player.getPlayerUsername(), player.getPlayerPassword());
				if(player != null) {
					player.setPlayerPassword("****");
					res.setStatus(HttpServletResponse.SC_ACCEPTED);
					out.println(om.writeValueAsString(player));
				}
			}catch(PasswordMatchFailed e) {
				String invalidPasswordMessage = "INVALID PASSWORD login fail: " + player.getPlayerUsername();
//				LoggerSingleton.getAccessLog().warn(invalidPasswordMessage);
				res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				out.println("{\"error\":\"User login failed\"}");
			}catch(UserNotFound e) {
				String invalidUsername = "USER NOT FOUND login fail: " + player.getPlayerUsername();
//				LoggerSingleton.getAccessLog().warn(invalidUsername);
				res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				out.println("{\"error\":\"User login failed\"}");
			}
		}else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			String invalidMessage = "Invalid request of type: " + type + " to NewAccountCommand. Body: " + body;
//			LoggerSingleton.getAccessLog().warn(invalidMessage);
		}
	}

}
