package com.rest.server.core;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.net.httpserver.HttpExchange;

public class RequestInfo {

	private String uri;
	private String ip;
	private String method;
	private long timestamp;
	
	public RequestInfo() {
		
	}
	
	public RequestInfo(HttpExchange exchange) {
		setTimestamp(System.currentTimeMillis());
		setUri(exchange.getRequestURI().getPath());
		setIp(exchange.getRemoteAddress().getAddress().toString());
		setMethod(exchange.getRequestMethod());
	}
	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("Incoming Request: ");
		builder.append(new SimpleDateFormat("MMM dd,yyyy HH:mm:ss").format(new Date(timestamp)));
		builder.append(" - ");
		builder.append(method);
		builder.append(" - ");
		builder.append(ip);
		builder.append(" - ");
		builder.append(uri);
		return builder.toString();
	}
	
}
