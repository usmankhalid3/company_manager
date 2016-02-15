package com.rest.server.core;

public class HttpMethod {
	
	public enum Method {
		GET,
		POST,
		PUT,
		PATCH,
		OPTIONS
	}
	
	public static boolean isGet(String method) {
		return check(method, Method.GET);
	}
	
	public static boolean isPost(String method) {
		return check(method, Method.POST);
	}
	
	public static boolean isPut(String method) {
		return check(method, Method.PUT);
	}
	
	public static boolean isPatch(String method) {
		return check(method, Method.PATCH);
	}
	
	public static boolean hasBody(String method) {
		return isPost(method) || isPut(method) || isPatch(method);
	}
	
	public static boolean isOptions(String method) {
		return check(method, Method.OPTIONS);
	}
	
	private static boolean check(String strMethod, Method method) {
		return method.toString().equals(strMethod.toUpperCase());
	}
}
