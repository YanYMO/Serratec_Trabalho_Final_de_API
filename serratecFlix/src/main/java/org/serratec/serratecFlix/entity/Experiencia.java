package org.serratec.serratecFlix.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "experiencia")
public class Experiencia {

	@Id
	private Long id;

	@MapsId
	@OneToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@Column(name = "xp", nullable = false)
	private Integer xp = 0;
	
	@Column(name = "nivel", nullable = false)
	private Integer nivel = 1;
	
	public Experiencia(Usuario usu) {
		this.usuario = usu;
	}
}
