package com.rest.server.core;

import com.rest.server.util.HttpStatusCode;

public class Response {

	private int statusCode;
	private String data;
	
	public Response(int code, String data) {
		this.statusCode = code;
		this.data = data;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getData() {
		if (!HttpStatusCode.isError(statusCode)) {
			return data;
		}
		else {
			return "{\"error\":true, \"message\":\"" + data + "\"}";
		}
	}

	public void setData(String data) {
		this.data = data;
	}
}
