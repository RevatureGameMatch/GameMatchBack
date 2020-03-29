package com.revature.g2g.api.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import com.revature.g2g.api.FrontCommand;

public class UnknownCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		out.println("{\"status:\":\"Unknown command requested.\"}");
	}

}
