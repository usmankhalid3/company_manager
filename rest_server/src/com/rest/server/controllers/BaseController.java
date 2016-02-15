package com.rest.server.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import com.rest.server.core.HttpMethod;
import com.rest.server.core.HttpProtocol;
import com.rest.server.util.HttpStatusCode;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

public abstract class BaseController implements HttpProtocol {

	protected ThreadLocal<HttpExchange> exchange = new ThreadLocal<HttpExchange>();
	
	public void handle(HttpExchange exchange) throws IOException {
		this.exchange.set(exchange);
		String method = exchange.getRequestMethod();
		@SuppressWarnings("unchecked")
		Map<String, Object> params = (Map<String, Object>)exchange.getAttribute("parameters");
		if (HttpMethod.isGet(method)) {
			handleGET(params);
		}
		else if (HttpMethod.isPost(method)) {
			handlePOST(params);
		}
		else if (HttpMethod.isPut(method)) {
			handlePUT(params);
		}
		else if (HttpMethod.isPatch(method)) {
			handlePATCH(params);
		}
		else if (HttpMethod.isOptions(method)) {
			success("OK");
		}
		else {
			error("Unhandled Method: " + method);
		}
	}
	
	protected void success(String resp) throws IOException {
		writeResponse(resp);
	}
	
	protected void error(String resp) throws IOException {
		error(HttpStatusCode.SERVER_ERROR, resp);
	}
	
	protected void error(int errorCode, String resp) throws IOException {
		exchange.get().sendResponseHeaders(errorCode, resp.getBytes().length);
		writeResponse(resp);
	}
	
	private void writeResponse(String text) throws IOException {
		Headers headers = exchange.get().getResponseHeaders();
		headers.set("Content-Type", "text/json");
		headers.set("Access-Control-Allow-Headers:", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		headers.set("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, OPTIONS");
		headers.set("Access-Control-Allow-Origin", "*");
		exchange.get().sendResponseHeaders(HttpStatusCode.OK, text.getBytes().length);		
		if (text != null) {
			OutputStream os = exchange.get().getResponseBody();
			os.write(text.getBytes());
			os.close();
		}
	}
}
