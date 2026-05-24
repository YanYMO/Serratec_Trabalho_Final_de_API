package org.serratec.serratecFlix.controller;

import org.serratec.serratecFlix.DTO.RequestDTO.AvaliacaoFilmeAtualizacaoDTO;
import org.serratec.serratecFlix.DTO.RequestDTO.AvalicaoFilmeRequestDTO;
import org.serratec.serratecFlix.DTO.ResponseDTO.AvaliacaoFilmeResponseDTO;
import org.serratec.serratecFlix.service.AvaliacaoFilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes-filmes")
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
    public ResponseEntity<AvaliacaoFilmeResponseDTO> criarAvaliacao(@RequestBody AvalicaoFilmeRequestDTO request) {
        return ResponseEntity.status(201).body(avaliacaoFilmeService.cadastrar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoFilmeResponseDTO> atualizarAvaliacao(@PathVariable Long id,
                                                               @RequestBody AvaliacaoFilmeAtualizacaoDTO request) {
        return ResponseEntity.ok(avaliacaoFilmeService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AvaliacaoFilmeResponseDTO> deletarAvaliacao(@PathVariable Long id) {
        avaliacaoFilmeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
