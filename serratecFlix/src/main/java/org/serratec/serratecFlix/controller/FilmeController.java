package org.serratec.serratecFlix.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.serratec.serratecFlix.dto.requestdto.FilmeRequestDTO;
import org.serratec.serratecFlix.dto.responsedto.FilmeResponseDTO;
import org.serratec.serratecFlix.dto.responsedto.OmdbFilmeResponseDTO;
import org.serratec.serratecFlix.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Filme", description = "Cadastro de filmes")
@RestController
@RequestMapping("/filmes")
@Validated
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @Operation(summary = "Listar todos os filmes", description = "Retorna uma lista com todos os filmes cadastrados")
    @GetMapping
    public ResponseEntity<List<FilmeResponseDTO>> listarFilmes() {
        return ResponseEntity.ok(filmeService.findAll());
    }

    @Operation(summary = "Buscar filme por ID", description = "Retorna um filme específico pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<FilmeResponseDTO> listarPorId(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(filmeService.findById(id, userDetails.getUsername()));
    }

    @Operation(summary = "Cadastrar filme", description = "Cadastra um novo filme no sistema")
    @PostMapping
    public ResponseEntity<FilmeResponseDTO> criarFilme(@Valid @RequestBody FilmeRequestDTO request) {
        return ResponseEntity.status(201).body(filmeService.cadastrar(request));
    }

    @Operation(summary = "Atualizar filme", description = "Atualiza os dados de um filme existente")
    @PutMapping("/{id}")
    public ResponseEntity<FilmeResponseDTO> atualizarFilme(@PathVariable Long id,
    													   @Valid @RequestBody FilmeRequestDTO request) {
        return ResponseEntity.ok(filmeService.atualizar(id, request));
    }

    @Operation(summary = "Deletar filme", description = "Remove um filme do sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<FilmeResponseDTO> deletarFilme(@PathVariable Long id) {
        filmeService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/externo")
    public ResponseEntity<OmdbFilmeResponseDTO> buscarExterno(@RequestParam String titulo) {
    return ResponseEntity.ok(filmeService.buscarApiExterna(titulo));
}
}
