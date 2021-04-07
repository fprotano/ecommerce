package it.exolab.ecommerce.exception;

public class FieldError extends Exception {

	private String field;

	public FieldError(String field) {
		super();
		this.field = field;
	}

	public String getDescription(Exception ex) {

		if(ex instanceof RequiredFieldError) {
			RequiredFieldError rq = (RequiredFieldError) ex;
			return "Il campo " + rq.getField()+ " è richiesto ";
		}
		if(ex instanceof MaxLengthError) {
			MaxLengthError rq = (MaxLengthError) ex;
			return "Il campo " + rq.getField()+ " deve essere al massimo di "+ rq.getMaxLength()+ " caratteri";
		}
		if(ex instanceof UniqueFieldError) {
			UniqueFieldError rq = (UniqueFieldError) ex;
			return "Il campo " + rq.getField()+ " deve essere univoco";
		}
		if(ex instanceof FormatError) {
			FormatError rq = (FormatError) ex;
			return "Il campo " + rq.getField()+ " non è nel formato previsto";
		}
		if(ex instanceof MinLengthError) {
			MinLengthError rq = (MinLengthError) ex;
			return "Il campo " + rq.getField()+ " deve essere minimo "+ rq.getMinLength()+ " caratteri";
		}
		if(ex instanceof MinValueError) {
			MinValueError rq = (MinValueError) ex;
			return "Per Il campo " + rq.getField()+ " inserire un valore minimo di "+ rq.getMin();
		}
		
		
		return "";

	}

	public String getField() {
		return field;
	}
	
	
	
	
	
	
}
