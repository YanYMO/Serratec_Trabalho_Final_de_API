package org.serratec.serratecFlix.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
	
	@Column(name = "xp")
	private Integer xp = 0;
	
	@Column(name = "nivel")
	private Integer nivel = 1;
}
