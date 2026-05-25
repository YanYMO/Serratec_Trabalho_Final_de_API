package org.serratec.serratecFlix.exception;

public class IdadeInsuficienteException extends RuntimeException{

    public IdadeInsuficienteException(String titulo, Integer idadeMinima) {
        super("Acesso negado: o conteudo => " + " <= é restrito ao perfil de menores de " + " anos.");
    }
}
