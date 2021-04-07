package it.exolab.ecommerce.exception;

public class GenericError extends Exception {
	public static final int C_999 = 999;
	private int code;
	public GenericError(int code) {
		this.code=code;
	}

	public int getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Errore sconosciuto n." + code+", contattare l'amministratore";
	}
	
	
	
}
