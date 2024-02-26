package com.firstdecision.cadatro.api.domain.exceptions;

public class ValidacaoException extends RuntimeException {

	private String nomeAtributo;

	public ValidacaoException(String nomeAtributo, String mensagem) {
		super(mensagem);
		this.nomeAtributo = nomeAtributo;
	}
	
	public ValidacaoException(String nomeAtributo, String mensagem, Throwable causa) {
		super(mensagem, causa);
		this.nomeAtributo = nomeAtributo;
	}

	public String getNomeAtributo() {
		return nomeAtributo;
	}
	
}
