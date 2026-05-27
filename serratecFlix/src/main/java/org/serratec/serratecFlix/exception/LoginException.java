package org.serratec.serratecFlix.exception;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginException implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
	        AuthenticationException authException) throws IOException {

	    ErroResposta erro = new ErroResposta(401,
	            "Acesso negado. Você precisa estar logado (Token ausente ou inválido)!");

	    ObjectMapper mapper = new ObjectMapper();
	    mapper.registerModule(new JavaTimeModule());
	    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

	    response.setContentType("application/json;charset=UTF-8");
	    response.setStatus(401);
	    response.getWriter().write(mapper.writeValueAsString(erro));
	}
}