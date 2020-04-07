package com.revature.g2g.api.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import com.revature.g2g.api.FrontCommand;
import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerRole;
import com.revature.g2g.services.handlers.PlayerHandler;
import com.revature.g2g.services.helpers.LoggerSingleton;
import com.revature.g2g.services.helpers.PasswordHelper;

public class PlayerCommand extends FrontCommand{

	@Override
	public void process() throws ServletException, IOException {

		if (type.equals("POST")) {
			PlayerHandler playerHandler = new PlayerHandler();
			Player template = om.readValue(body,  Player.class);
			Player usernameCheck = playerHandler.findByUsername(template.getPlayerUsername());
			if(usernameCheck == null) {
//				template.setPlayerPassword(PasswordHelper.encryptPassword(template.getPlayerPassword()));
				template.setPlayerRole(PlayerRole.PLAYER);
				playerHandler.insert(template);
				Player result = playerHandler.findByUsername(template.getPlayerUsername());
				result.setPlayerPassword("****");
				res.setStatus(HttpServletResponse.SC_ACCEPTED);
				out.println(om.writeValueAsString(result));
			}else {
				out.println("{\"message\":\"Username Taken\"}");
				res.setStatus(HttpServletResponse.SC_CONFLICT);
			}
		}else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			String invalidMessage = "Invalid request of type: " + type + " to NewAccountCommand. Body: " + body;
//			LoggerSingleton.getAccessLog().warn(invalidMessage);
		}
	}

}
