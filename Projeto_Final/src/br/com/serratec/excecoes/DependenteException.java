package br.com.serratec.excecoes;

public class DependenteException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public DependenteException (String message) {
		super(message);
	}
}
