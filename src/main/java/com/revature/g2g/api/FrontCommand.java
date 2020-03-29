package com.revature.g2g.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.g2g.services.helpers.LoggerSingleton;

public abstract class FrontCommand {
	protected static final ObjectMapper om = new ObjectMapper();
	protected ServletContext context;
	protected HttpServletRequest req;
	protected HttpServletResponse res;
	protected String body;
	protected PrintWriter out;
	protected String type;
	public void init(ServletContext context, HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			CORSFilter.doFilter(req,res);
		} catch (IOException e) {
			LoggerSingleton.getExceptionLogger().warn("FrontCommand: CORSFilter failed to be applied 1.");
		} catch (ServletException e) {
			LoggerSingleton.getExceptionLogger().warn("FrontCommand: CORSFilter failed to be applied 2.");
		}
		this.context = context;
		this.req = req;
		this.res = res;
		this.out = res.getWriter();
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line = "";
		while((line = reader.readLine()) != null) {
			sb.append(line);
		}
		this.body = sb.toString();
	}
	public abstract void process() throws ServletException, IOException;
	
	protected void forward (String target) throws ServletException, IOException{
		target = String.format("/WEB-INF/%s.html",target);
		RequestDispatcher dispatcher = context.getRequestDispatcher(target);
		dispatcher.forward(req, res);
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
