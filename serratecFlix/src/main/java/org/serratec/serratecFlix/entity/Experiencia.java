package org.serratec.serratecFlix.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

@Entity
@Table(name = "experiencia")
public class Experiencia {
	
	@Id
	private Integer id;

	@MapsId
	@OneToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@Column(name = "xp", nullable = false)
	private Integer xp;
	
	@Column(name = "nivel", nullable = false)
	private Integer nivel;
}
