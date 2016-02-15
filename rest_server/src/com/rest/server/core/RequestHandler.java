package com.rest.server.core;

import java.io.IOException;
import java.net.URI;

import com.rest.server.controllers.CompanyController;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class RequestHandler implements HttpHandler {

	public void handle(HttpExchange exchange) throws IOException {
		String method = exchange.getRequestMethod();
		URI uri = exchange.getRequestURI();
		System.out.println("Incoming request: " + method + " - " + uri);
		new CompanyController().handle(exchange);
	}
}
