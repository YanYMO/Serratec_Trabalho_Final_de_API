package org.serratec.serratecFlix.controller;

import org.serratec.serratecFlix.DTO.RequestDTO.FilmeRequestDTO;
import org.serratec.serratecFlix.DTO.ResponseDTO.FilmeResponseDTO;
import org.serratec.serratecFlix.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping
    public ResponseEntity<List<FilmeResponseDTO>> listarFilmes() {
        return ResponseEntity.ok(filmeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmeResponseDTO> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(filmeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<FilmeResponseDTO> criarFilme(@RequestBody FilmeRequestDTO request) {
        return ResponseEntity.status(201).body(filmeService.cadastrar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmeResponseDTO> atualizarFilme(@PathVariable Long id,
                                                                   @RequestBody FilmeRequestDTO request) {
        return ResponseEntity.ok(filmeService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FilmeResponseDTO> deletarFilme(@PathVariable Long id) {
        filmeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
