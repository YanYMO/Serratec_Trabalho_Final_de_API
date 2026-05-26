/*
package org.serratec.serratecFlix.controller;

import org.serratec.serratecFlix.entity.Experiencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/experiencias")
public class ExperienciaController {
	
	@Autowired
	private Experiencia experiencia;
	
	@GetMapping
	public ResponseEntity<Experiencia> pegarID (@AuthenticationPrincipal UserDetails userDetails){
		return ResponseEntity.ok()
	}
}
*/
