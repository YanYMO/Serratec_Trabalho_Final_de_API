package org.serratec.serratecFlix.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Lista de Favoritos", description = "Cadastro de filmes e séries favoritos")
@RestController
@RequestMapping("/listas-favoritos")
@Validated
public class ListaFavoritosController {
    
    @Autowired
    private ListaFavoritosService listaFavoritosService;

    @Operation(summary = "Listar a lista de favoritos", description = "Retorna uma lista de filmes e séries favoritos")
    @GetMapping
    public ResponseEntity<List<ListaFavoritosResponseDTO>> listarLista() {
        return ResponseEntity.ok(listaFavoritosService.findAll());
    }

    @Operation(summary = "Buscar lista de favoritos por ID", description = "Retorna uma lista de favoritos específica pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<ListaFavoritosResponseDTO> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(listaFavoritosService.findById(id));
    }

    @Operation(summary = "Cadastrar lista de favoritos", description = "Cadastra uma nova lista de favoritos no sistema")
    @PostMapping
    public ResponseEntity<ListaFavoritosResponseDTO> criarLista(@Valid @RequestBody ListaFavoritosRequestDTO request) {
        return ResponseEntity.status(201).body(listaFavoritosService.cadastrar(request));
    }

    @Operation(summary = "Atualizar lista de favoritos", description = "Atualiza os dados de uma lista de favoritos existente")
    @PutMapping("/{id}")
    public ResponseEntity<ListaFavoritosResponseDTO> atualizarLista(@PathVariable Long id,
    																@Valid @RequestBody ListaFavoritosRequestDTO request) {
        return ResponseEntity.ok(listaFavoritosService.atualizar(id, request));
    }

    @Operation(summary = "Deletar lista de favoritos", description = "Remove uma lista de favoritos do sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<ListaFavoritosResponseDTO> deletarLista(@PathVariable Long id) {
        listaFavoritosService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
