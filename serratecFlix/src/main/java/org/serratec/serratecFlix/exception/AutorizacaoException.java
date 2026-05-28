package org.serratec.serratecFlix.exception;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AutorizacaoException implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
	        AccessDeniedException accessDeniedException) throws IOException {

	    ErroResposta erro = new ErroResposta(403,
	            "Acesso negado. Você não tem permissão para acessar este endPoint!");

	    ObjectMapper mapper = new ObjectMapper();
	    mapper.registerModule(new JavaTimeModule());
	    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); 

	    response.setContentType("application/json;charset=UTF-8");
	    response.setStatus(403);
	    response.getWriter().write(mapper.writeValueAsString(erro));
	}
}