package org.serratec.serratecFlix.controller;

import org.serratec.serratecFlix.DTO.RequestDTO.SerieRequestDTO;
import org.serratec.serratecFlix.DTO.ResponseDTO.SerieResponseDTO;
import org.serratec.serratecFlix.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/series")
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
    public ResponseEntity<SerieResponseDTO> criarSerie(@RequestBody SerieRequestDTO request) {
        return ResponseEntity.status(201).body(serieService.cadastrar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SerieResponseDTO> atualizarSerie(@PathVariable Long id,
                                                               @RequestBody SerieRequestDTO request) {
        return ResponseEntity.ok(serieService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SerieResponseDTO> deletarSerie(@PathVariable Long id) {
        serieService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
