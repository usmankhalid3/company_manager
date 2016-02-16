package com.rest.server.core;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.rest.server.config.ServerConfig;
import com.rest.server.storage.CompanyManager;
import com.rest.server.util.Logger;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

public class Server {
	
	private HttpServer server;
	private int port;

	public Server(int port) {
		try {
			this.port = port;
			initServer();
		}
		catch (IOException e) {
			throw new RuntimeException("Failed to init the server: " + e.getMessage());
		}
	}
	
	private void initServer() throws IOException {
		this.server = HttpServer.create(new InetSocketAddress(this.port), 0);
		HttpContext context = this.server.createContext("/", new RequestHandler());
		context.getFilters().add(new ParameterFilter());
		this.server.setExecutor(Executors.newFixedThreadPool(ServerConfig.getPoolSize()));
		CompanyManager.getInstance().connect();
	}
	
	public void start() {
		this.server.start();
		Logger.stdOut("Server running on port: " + this.port );
	}
	
	public static void main(String[] args) {
		Server server = new Server(ServerConfig.getPort());
		server.start();
	}
}
