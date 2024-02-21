package com.firstdecision.cadatro.api.domain.exceptions;

public class NegocioException extends Exception {

	private static final long serialVersionUID = 1L;

	public NegocioException(String mensagem) {
		super(mensagem);
	}
	
	public NegocioException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}
