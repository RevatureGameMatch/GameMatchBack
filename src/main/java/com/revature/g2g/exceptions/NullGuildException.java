package com.revature.g2g.exceptions;

public class NullGuildException extends RuntimeException {
	private static final long serialVersionUID = -6103524411431939700L;
	public NullGuildException() {
		super();
	}
	public NullGuildException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
	public NullGuildException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
	public NullGuildException(String arg0) {
		super(arg0);
	}
	public NullGuildException(Throwable arg0) {
		super(arg0);
	}
}