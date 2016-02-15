package com.rest.server.config;

public class ServerConfig {

	private static int port = 1435;
	private static int poolSize = 50;
	
	public static int getPort() {
		return port;
	}
	
	public static int getPoolSize() {
		return poolSize;
	}
}
