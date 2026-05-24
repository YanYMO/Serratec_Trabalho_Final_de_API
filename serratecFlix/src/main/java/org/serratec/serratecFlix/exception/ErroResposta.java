package org.serratec.serratecFlix.exception;

public class ErroResposta {
	
	private Integer status;
	private String menssagem;
	
	public ErroResposta(Integer status, String menssagem) {
		super();
		this.status = status;
		this.menssagem = menssagem;
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
}
