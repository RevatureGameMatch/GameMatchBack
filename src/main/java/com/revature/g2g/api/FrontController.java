package com.revature.g2g.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.g2g.api.commands.UnknownCommand;
import com.revature.g2g.exceptions.ConnectionToDatabaseFailed;

public class FrontController extends HttpServlet{
	private static final long serialVersionUID = 7302326277418684325L;
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		FrontCommand command = getCommand(req);
		command.setType("OPTIONS");
		command.init(getServletContext(),req,res);
		res.setStatus(HttpServletResponse.SC_ACCEPTED);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		FrontCommand command = getCommand(req);
		command.setType("GET");
		command.init(getServletContext(),req,res);
		try {
			command.process();
		}catch(ConnectionToDatabaseFailed e) {
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		FrontCommand command = getCommand(req);
		command.setType("POST");
		command.init(getServletContext(),req,res);
		try {
			command.process();
		}catch(ConnectionToDatabaseFailed e) {
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		FrontCommand command = getCommand(req);
		command.setType("PUT");
		command.init(getServletContext(),req,res);
		try {
			command.process();
		}catch(ConnectionToDatabaseFailed e) {
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		FrontCommand command = getCommand(req);
		command.setType("DELETE");
		command.init(getServletContext(),req,res);
		try {
			command.process();
		}catch(ConnectionToDatabaseFailed e) {
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
	private FrontCommand getCommand(HttpServletRequest req) {
		try {
			final String URI = req.getRequestURI();
			String[] uriArray = URI.split("/");
			if(uriArray.length > 2) {
				String firstUri = uriArray[2];
				String classPath = "com.revature.g2g.api.commands." + firstUri + "Command";
				Class<?> type = Class.forName(classPath);
				return (FrontCommand) type.asSubclass(FrontCommand.class).newInstance();
			}else {
				return new UnknownCommand();
			}
		}catch (Exception e) {
			return new UnknownCommand();
		}
	}

}
