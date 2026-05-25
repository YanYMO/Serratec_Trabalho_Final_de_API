package org.serratec.serratecFlix.controller;

import org.serratec.serratecFlix.DTO.RequestDTO.AvaliacaoSerieRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("avaliacoes-series")
public class AvaliacaoSerieController {
    
    @Autowired
    private AvaliacaoSerieService avaliacaoSerieService;

    @GetMapping
    public ResponseEntity<List<AvaliacaoSerieResponseDTO>> listarAvaliacoes() {
        return ResponseEntity.ok(avaliacaoSerieService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoSerieResponseDTO> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(avaliacaoSerieService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AvaliacaoSerieResponseDTO> criarAvaliacao(@RequestBody AvaliacaoSerieRequestDTO request) {
        return ResponseEntity.status(201).body(avaliacaoSerieService.cadastrar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoSerieResponseDTO> atualizarAvaliacao(@PathVariable Long id,
                                                               @RequestBody AvaliacaoSerieRequestDTO request) {
        return ResponseEntity.ok(avaliacaoSerieService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AvaliacaoSerieResponseDTO> deletarAvaliacao(@PathVariable Long id) {
        avaliacaoSerieService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
