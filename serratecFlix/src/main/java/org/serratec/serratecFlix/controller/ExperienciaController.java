package org.serratec.serratecFlix.controller;

import java.util.List;

import org.serratec.serratecFlix.dto.responsedto.ExperienciaResponseDTO;
import org.serratec.serratecFlix.service.ExperienciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Experiência", description = "Acesso às Experiências")
@RestController
@RequestMapping("/experiencia")
public class ExperienciaController {
	
	@Autowired
	private ExperienciaService experienciaService;
	
	@GetMapping("/{id}")
	public ResponseEntity<ExperienciaResponseDTO> listarPorId(@PathVariable Long id){
		
		return ResponseEntity.ok(experienciaService.findById(id));
	}
	
	@GetMapping()
	public ResponseEntity<List<ExperienciaResponseDTO>> listarTodos(){
		return ResponseEntity.ok(experienciaService.findAll());
	}
	
	@GetMapping("/meuxp")
	public ResponseEntity<ExperienciaResponseDTO> minhaExp(@AuthenticationPrincipal UserDetails userDetails){
		
		return ResponseEntity.ok(experienciaService.findByUsername(userDetails.getUsername()));
	}
}
