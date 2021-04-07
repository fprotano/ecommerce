package it.exolab.ecommerce.framework;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class UrlAndMethod extends MappedAnnotation {
	private String url;
	private Method method;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	
	public UrlAndMethod(String url, Method method, Annotation annotation) {
		super(annotation);
		this.url = url;
		this.method = method;
		
	}
	
	
}
