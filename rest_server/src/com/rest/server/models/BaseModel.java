package com.rest.server.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

abstract public class BaseModel {
	
	protected int id;
	protected String createdAt;
	protected String updatedAt;
	public static Gson gson = new GsonBuilder().create();
	
	public String toJson() {
		return gson.toJson(this);
	}
	
	public static <T extends BaseModel> T fromJson(String json, Class<T> c) {
		return gson.fromJson(json, c);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
