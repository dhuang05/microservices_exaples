package com.nusino.lab.microservices.util.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonBuilder {
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public static JsonBuilder map() {
		return new JsonBuilder();
	}
	
	public <T> String toJson(T object) {
		try {
			return objectMapper.writeValueAsString(object);
		}catch(JsonProcessingException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public <T> T fromJson(String json, Class<T> type) {
		return objectMapper.convertValue(json, type);
	}
}
