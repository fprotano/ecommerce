package it.exolab.ecommerce.framework;

import java.lang.annotation.Annotation;

public class MappedAnnotation {
	private Annotation annotation;

	public Annotation getAnnotation() {
		return annotation;
	}

	public void setAnnotation(Annotation annotation) {
		this.annotation = annotation;
	}

	public MappedAnnotation(Annotation annotation) {
		super();
		this.annotation = annotation;
	}
	
	
}
