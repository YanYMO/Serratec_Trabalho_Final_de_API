package org.serratec.serratecFlix.controller;

import java.util.List;

import org.serratec.serratecFlix.dto.requestdto.AvaliacaoFilmeAtualizacaoDTO;
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

@RestController
@RequestMapping("/avaliacoes-filmes")
@Validated
public class AvaliacaoFilmeController {
    
    @Autowired
    private AvaliacaoFilmeService avaliacaoFilmeService;

    @GetMapping
    public ResponseEntity<List<AvaliacaoFilmeResponseDTO>> listarAvaliacoes() {
        return ResponseEntity.ok(avaliacaoFilmeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoFilmeResponseDTO> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(avaliacaoFilmeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AvaliacaoFilmeResponseDTO> criarAvaliacao(@Valid @RequestBody AvalicaoFilmeRequestDTO request) {
        return ResponseEntity.status(201).body(avaliacaoFilmeService.cadastrar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoFilmeResponseDTO> atualizarAvaliacao(@PathVariable Long id,
    																	@Valid @RequestBody AvaliacaoFilmeAtualizacaoDTO request) {
        return ResponseEntity.ok(avaliacaoFilmeService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AvaliacaoFilmeResponseDTO> deletarAvaliacao(@PathVariable Long id) {
        avaliacaoFilmeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
