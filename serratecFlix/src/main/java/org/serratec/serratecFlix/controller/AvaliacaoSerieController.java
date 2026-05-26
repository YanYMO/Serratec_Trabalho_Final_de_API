package org.serratec.serratecFlix.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.serratec.serratecFlix.dto.requestdto.AvaliacaoAtualizacaoDTO;
import org.serratec.serratecFlix.dto.requestdto.AvaliacaoSerieRequestDTO;
import org.serratec.serratecFlix.dto.responsedto.AvaliacaoSerieResponseDTO;
import org.serratec.serratecFlix.service.AvaliacaoSerieService;
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

@Tag(name = "Avaliação de Séries", description = "Cadastro de avaliações de séries")
@RestController
@RequestMapping("avaliacoes-series")
@Validated
public class AvaliacaoSerieController {
    
    @Autowired
    private AvaliacaoSerieService avaliacaoSerieService;

    @Operation(summary = "Listar todas as avaliações de série", description = "Retorna uma lista com todos as avaliações de série cadastradas")
    @GetMapping
    public ResponseEntity<List<AvaliacaoSerieResponseDTO>> listarAvaliacoes() {
        return ResponseEntity.ok(avaliacaoSerieService.findAll());
    }

    @Operation(summary = "Buscar avaliação de série por ID", description = "Retorna uma avaliação de série específica pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoSerieResponseDTO> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(avaliacaoSerieService.findById(id));
    }

    @Operation(summary = "Cadastrar avaliação de série", description = "Cadastra uma nova avaliação de série no sistema")
    @PostMapping
    public ResponseEntity<AvaliacaoSerieResponseDTO> criarAvaliacao(@Valid @RequestBody AvaliacaoSerieRequestDTO request) {
        return ResponseEntity.status(201).body(avaliacaoSerieService.cadastrar(request));
    }

    @Operation(summary = "Atualizar avaliação de série", description = "Atualiza os dados de uma avaliação de série existente")
    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoSerieResponseDTO> atualizarAvaliacao(@PathVariable Long id,
    																	@Valid @RequestBody AvaliacaoAtualizacaoDTO request) {
        return ResponseEntity.ok(avaliacaoSerieService.atualizar(id, request));
    }

    @Operation(summary = "Deletar avaliação de série", description = "Remove uma avaliação de série do sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<AvaliacaoSerieResponseDTO> deletarAvaliacao(@PathVariable Long id) {
        avaliacaoSerieService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
