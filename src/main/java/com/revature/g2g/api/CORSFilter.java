package com.revature.g2g.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CORSFilter {

	/**
	 * Default Constructor
	 */
	private CORSFilter() {
	}
	
	public static void doFilter(HttpServletRequest req, HttpServletResponse res) {
//		res.addHeader("Access-Control-Allow-Origin","https://g2g.revatureprojects.com");
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");
		res.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization, reimbursmentid, submitter");
		if(req.getMethod().equals("OPTIONS")) {
			res.setStatus(HttpServletResponse.SC_ACCEPTED);
		}
	}
}
