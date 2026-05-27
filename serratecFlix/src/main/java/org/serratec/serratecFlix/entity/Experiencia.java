package org.serratec.serratecFlix.entity;

import jakarta.persistence.*;
import lombok.*;

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
	private Integer xp;
	
	@Column(name = "nivel", nullable = false)
	private Integer nivel;
}
