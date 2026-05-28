package org.serratec.serratecFlix.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErroResposta {
	
	private Integer status;
	private String mensagem;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataHora;
	
	public ErroResposta(Integer status, String mensagem) {
		super();
		this.status = status;
		this.mensagem = mensagem;
		this.setDataHora(LocalDateTime.now());
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
}
