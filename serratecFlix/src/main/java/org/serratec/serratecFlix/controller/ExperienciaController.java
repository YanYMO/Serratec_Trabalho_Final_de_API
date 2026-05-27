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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Experiência", description = "Acesso às Experiências")
@RestController
@RequestMapping("/experiencias")
public class ExperienciaController {
	
	@Autowired
	private ExperienciaService experienciaService;
	
	@Operation(summary = "Listar todas as Experiências", description = "Retorna uma lista com todas as Experiências cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Experiências retornada com sucesso!")
    })
	@GetMapping()
	public ResponseEntity<List<ExperienciaResponseDTO>> listarTodos(){
		return ResponseEntity.ok(experienciaService.findAll());
	}
	
	@Operation(summary = "Buscar uma experiência por ID", description = "Retorna uma experiência específica pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Experiência encontrada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Experiência não encontrada")
    })
	@GetMapping("/{id}")
	public ResponseEntity<ExperienciaResponseDTO> listarPorId(@PathVariable Long id){
		
		return ResponseEntity.ok(experienciaService.findById(id));
	}
	
	@Operation(summary = "Buscar a experiência do usuario logado", description = "Retorna uma experiência específica do usuario logado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Experiência encontrado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Experiência não encontrada")
    })
	@GetMapping("/meuxp")
	public ResponseEntity<ExperienciaResponseDTO> minhaExp(@AuthenticationPrincipal UserDetails userDetails){
		
		return ResponseEntity.ok(experienciaService.findByUsername(userDetails.getUsername()));
	}
}
