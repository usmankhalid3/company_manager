package com.rest.server.core;

import java.io.IOException;

import com.rest.server.controllers.CompanyController;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class RequestHandler implements HttpHandler {

	public void handle(HttpExchange exchange) throws IOException {
		System.out.println(new RequestInfo(exchange));
		new CompanyController().handle(exchange);
	}
}
