package it.exolab.ecommerce.framework;

import java.lang.annotation.Annotation;
import java.util.HashMap;

public class UrlAndClassMapping  extends MappedAnnotation {

	private String url;
	private Class type;
	private HashMap<String,UrlAndMethod> methods = new  HashMap<String,UrlAndMethod>();
	
	
	public HashMap<String, UrlAndMethod> getMethods() {
		return methods;
	}
	public void setMethods(HashMap<String, UrlAndMethod> methods) {
		this.methods = methods;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Class getType() {
		return type;
	}
	public void setType(Class type) {
		this.type = type;
	}
	public UrlAndClassMapping(String url, Class type, Annotation annotation) {
		super(annotation);
		this.url = url;
		this.type = type;
	}
	
}
