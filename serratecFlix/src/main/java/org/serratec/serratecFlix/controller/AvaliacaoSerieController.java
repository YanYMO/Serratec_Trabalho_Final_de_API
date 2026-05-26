
package org.serratec.serratecFlix.controller;

import java.util.List;

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

@RestController
@RequestMapping("avaliacoes-series")
@Validated
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
    public ResponseEntity<AvaliacaoSerieResponseDTO> criarAvaliacao(@Valid @RequestBody AvaliacaoSerieRequestDTO request) {
        return ResponseEntity.status(201).body(avaliacaoSerieService.cadastrar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoSerieResponseDTO> atualizarAvaliacao(@PathVariable Long id,
    																	@Valid @RequestBody AvaliacaoSerieRequestDTO request) {
        return ResponseEntity.ok(avaliacaoSerieService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AvaliacaoSerieResponseDTO> deletarAvaliacao(@PathVariable Long id) {
        avaliacaoSerieService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

