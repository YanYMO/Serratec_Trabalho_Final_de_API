
package org.serratec.serratecFlix.controller;

import java.util.List;

import org.serratec.serratecFlix.dto.requestdto.ListaFavoritosRequestDTO;
import org.serratec.serratecFlix.dto.responsedto.ListaFavoritosResponseDTO;
import org.serratec.serratecFlix.service.ListaFavoritosService;
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
@RequestMapping("/listas-favoritos")
@Validated
public class ListaFavoritosController {
    
    @Autowired
    private ListaFavoritosService listaFavoritosService;

    @GetMapping
    public ResponseEntity<List<ListaFavoritosResponseDTO>> listarLista() {
        return ResponseEntity.ok(listaFavoritosService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaFavoritosResponseDTO> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(listaFavoritosService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ListaFavoritosResponseDTO> criarLista(@Valid @RequestBody ListaFavoritosRequestDTO request) {
        return ResponseEntity.status(201).body(listaFavoritosService.cadastrar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListaFavoritosResponseDTO> atualizarLista(@PathVariable Long id,
    																@Valid @RequestBody ListaFavoritosRequestDTO request) {
        return ResponseEntity.ok(listaFavoritosService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ListaFavoritosResponseDTO> deletarLista(@PathVariable Long id) {
        listaFavoritosService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

