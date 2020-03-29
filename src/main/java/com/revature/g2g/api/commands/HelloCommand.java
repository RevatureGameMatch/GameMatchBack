package com.revature.g2g.api.commands;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import com.revature.g2g.api.FrontCommand;

public class HelloCommand extends FrontCommand {
	@Override
	public void process() throws ServletException, IOException {
		if (type.equals("GET")) {
			PrintWriter out = res.getWriter();
			out.println("{\"message\":\"Hello World\"}");
		}else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
	}
}
