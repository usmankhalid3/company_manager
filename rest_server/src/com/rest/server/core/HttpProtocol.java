package com.rest.server.core;

import java.io.IOException;
import java.util.Map;

public interface HttpProtocol {

	public void handleGET(Map<String, Object> params) throws IOException;
	public void handlePOST(Map<String, Object> params) throws IOException;
	public void handlePUT(Map<String, Object> params) throws IOException;
	public void handlePATCH(Map<String, Object> params) throws IOException;
	public void handleDELETE(Map<String, Object> params) throws IOException;
}
