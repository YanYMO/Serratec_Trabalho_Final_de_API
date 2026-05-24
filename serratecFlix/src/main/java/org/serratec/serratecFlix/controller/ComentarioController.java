package org.serratec.serratecFlix.controller;

import java.util.List;

import org.serratec.serratecFlix.RequestDTO.ComentarioRequestDTO;
import org.serratec.serratecFlix.ResponseDTO.ComentarioResponseDTO;
import org.serratec.serratecFlix.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @PostMapping
    public ResponseEntity<ComentarioResponseDTO> criar(@RequestBody @Valid ComentarioRequestDTO comentarioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioService.criar(comentarioDTO));
    }

    @GetMapping("/filme/{filmeId}")
    public ResponseEntity<List<ComentarioResponseDTO>> buscarPorFilme(@PathVariable Long filmeId) {
        return ResponseEntity.ok(comentarioService.buscarPorFilme(filmeId));
    }

    @GetMapping("/serie/{serieId}")
    public ResponseEntity<List<ComentarioResponseDTO>> buscarPorSerie(@PathVariable Long serieId) {
        return ResponseEntity.ok(comentarioService.buscarPorSerie(serieId));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ComentarioResponseDTO>> buscarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(comentarioService.buscarPorUsuario(usuarioId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComentarioResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ComentarioRequestDTO comentarioDTO) {
        return ResponseEntity.ok(comentarioService.atualizar(id, comentarioDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        comentarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
