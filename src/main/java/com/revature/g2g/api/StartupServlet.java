package com.revature.g2g.api;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.revature.g2g.services.helpers.LoggerSingleton;
import com.revature.g2g.services.jda.JDASingleton;

public class StartupServlet extends HttpServlet{
	private static final long serialVersionUID = 1774848818025505706L;
	@Override
	public void init(final ServletConfig config) throws ServletException{
		new LoggerSingleton().getBusinessLog().trace("JDA Servlet Initializing");
		JDASingleton.getJda();
	}
}