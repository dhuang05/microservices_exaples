package com.nusino.lab.microservices.config.common;

public class RequestInfoKeeper {
	static ThreadLocal<String> globalRequestIdKeeper = new ThreadLocal<>();
	
	public static String getGlobalRequestId() {
		return globalRequestIdKeeper.get();
	}
	
	public static String setGlobalRequestId(String requestId) {
		globalRequestIdKeeper.set(requestId);
		return requestId;
	}
}
