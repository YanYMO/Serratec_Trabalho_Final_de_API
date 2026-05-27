package org.serratec.serratecFlix.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErroResposta {
	
	private Integer status;
	private String menssagem;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataHora;
	
	public ErroResposta(Integer status, String menssagem) {
		super();
		this.status = status;
		this.menssagem = menssagem;
		this.setDataHora(LocalDateTime.now());
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMenssagem() {
		return menssagem;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
}
