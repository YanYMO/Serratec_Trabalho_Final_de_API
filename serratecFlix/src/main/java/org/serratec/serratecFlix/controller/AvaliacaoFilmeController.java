package org.serratec.serratecFlix.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.serratec.serratecFlix.dto.requestdto.AvaliacaoAtualizacaoDTO;
import org.serratec.serratecFlix.dto.requestdto.AvalicaoFilmeRequestDTO;
import org.serratec.serratecFlix.dto.responsedto.AvaliacaoFilmeResponseDTO;
import org.serratec.serratecFlix.service.AvaliacaoFilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@Tag(name = "Avaliação de Filmes", description = "Cadastro de avaliações de filmes")
@RestController
@RequestMapping("/avaliacoes-filmes")
@Validated
public class AvaliacaoFilmeController {
    
    @Autowired
    private AvaliacaoFilmeService avaliacaoFilmeService;

    @Operation(summary = "Listar todas as avaliações de filme", description = "Retorna uma lista com todas as avaliações de filme cadastradas")
    @GetMapping
    public ResponseEntity<List<AvaliacaoFilmeResponseDTO>> listarAvaliacoes() {
        return ResponseEntity.ok(avaliacaoFilmeService.findAll());
    }

    @Operation(summary = "Buscar avaliação de filme por ID", description = "Retorna uma avaliação de filme específica pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoFilmeResponseDTO> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(avaliacaoFilmeService.findById(id));
    }

    @Operation(summary = "Cadastrar avaliação de filme", description = "Cadastra uma nova avaliação de filme no sistema")
    @PostMapping
    public ResponseEntity<AvaliacaoFilmeResponseDTO> criarAvaliacao(@Valid @RequestBody AvalicaoFilmeRequestDTO request) {
        return ResponseEntity.status(201).body(avaliacaoFilmeService.cadastrar(request));
    }

    @Operation(summary = "Atualizar avaliação de filme", description = "Atualiza os dados de uma avaliação de filme existente")
    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoFilmeResponseDTO> atualizarAvaliacao(@PathVariable Long id,
    																	@Valid @RequestBody AvaliacaoAtualizacaoDTO request) {
        return ResponseEntity.ok(avaliacaoFilmeService.atualizar(id, request));
    }

    @Operation(summary = "Deletar avaliação de filme", description = "Remove uma avaliação de filme do sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<AvaliacaoFilmeResponseDTO> deletarAvaliacao(@PathVariable Long id) {
        avaliacaoFilmeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
