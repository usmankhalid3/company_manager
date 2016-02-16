package com.rest.server.util;

public class HttpStatusCode {
	public static final int OK = 200;
	public static final int TARGET_UNRESOLVABLE = 400;
	public static final int SERVER_ERROR = 500;
	
	public static boolean isError(int code) {
		return code != OK;
	}
}
