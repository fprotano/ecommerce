package it.exolab.ecommerce.framework;

import java.util.HashMap;

public class Config {

	private HashMap<String,UrlAndClassMapping> controllers = new HashMap<String,UrlAndClassMapping>();

	public HashMap<String, UrlAndClassMapping> getControllers() {
		return controllers;
	}

	public void setControllers(HashMap<String, UrlAndClassMapping> controllers) {
		this.controllers = controllers;
	}
	
}
