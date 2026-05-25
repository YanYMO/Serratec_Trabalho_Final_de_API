package org.serratec.serratecFlix.controller;

import java.util.List;

import org.serratec.serratecFlix.dto.requestdto.SerieRequestDTO;
import org.serratec.serratecFlix.dto.responsedto.SerieResponseDTO;
import org.serratec.serratecFlix.service.SerieService;
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
@RequestMapping("/series")
@Validated
public class SerieController {
    
    @Autowired
    private SerieService serieService;

    @GetMapping
    public ResponseEntity<List<SerieResponseDTO>> listarSeries() {
        return ResponseEntity.ok(serieService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SerieResponseDTO> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(serieService.findById(id));
    }

    @PostMapping
    public ResponseEntity<SerieResponseDTO> criarSerie(@Valid @RequestBody SerieRequestDTO request) {
        return ResponseEntity.status(201).body(serieService.cadastrar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SerieResponseDTO> atualizarSerie(@PathVariable Long id,
    													   @Valid @RequestBody SerieRequestDTO request) {
        return ResponseEntity.ok(serieService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SerieResponseDTO> deletarSerie(@PathVariable Long id) {
        serieService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
