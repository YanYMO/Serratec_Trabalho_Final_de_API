package org.serratec.serratecFlix.exception;

public class IdadeInsuficienteException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

    public IdadeInsuficienteException(String titulo, Integer idadeMinima) {
        super("Acesso negado: o conteudo => " + titulo + " <= é restrito ao perfil de menores de " + idadeMinima + " anos.");
    }
}
