/*
package org.serratec.serratecFlix.controller;

import org.serratec.serratecFlix.DTO.RequestDTO.ListaFavoritosRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/listas-favoritos")
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
    public ResponseEntity<ListaFavoritosResponseDTO> criarLista(@RequestBody ListaFavoritosRequestDTO request) {
        return ResponseEntity.status(201).body(listaFavoritosService.cadastrar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListaFavoritosResponseDTO> atualizarLista(@PathVariable Long id,
                                                               @RequestBody ListaFavoritosRequestDTO request) {
        return ResponseEntity.ok(listaFavoritosService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ListaFavoritosResponseDTO> deletarLista(@PathVariable Long id) {
        listaFavoritosService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
*/
