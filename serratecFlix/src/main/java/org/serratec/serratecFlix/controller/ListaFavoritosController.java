package org.serratec.serratecFlix.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.serratec.serratecFlix.dto.requestdto.ListaFavoritosRequestDTO;
import org.serratec.serratecFlix.dto.responsedto.ListaFavoritosResponseDTO;
import org.serratec.serratecFlix.service.ListaFavoritosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Lista de Favoritos", description = "Cadastro de filmes e séries favoritos")
@RestController
@RequestMapping("/listas-favoritos")
@Validated
public class ListaFavoritosController {
    
    @Autowired
    private ListaFavoritosService listaFavoritosService;

    @Operation(summary = "Listar a lista de favoritos", description = "Retorna uma lista de filmes e séries favoritos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de filmes e séries favoritos retornada com sucesso!")
    })
    @GetMapping
    public ResponseEntity<List<ListaFavoritosResponseDTO>> listarLista() {
        return ResponseEntity.ok(listaFavoritosService.findAll());
    }

    @Operation(summary = "Buscar lista de favoritos por ID", description = "Retorna uma lista de favoritos específica pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de favoritos encontrada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Lista de favoritos não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ListaFavoritosResponseDTO> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(listaFavoritosService.findById(id));
    }

    @Operation(summary = "Cadastrar lista de favoritos", description = "Cadastra uma nova lista de favoritos no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lista de favoritos cadastrada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<ListaFavoritosResponseDTO> criarLista(@Valid @RequestBody ListaFavoritosRequestDTO request,
                                                                @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(201).body(listaFavoritosService.cadastrar(request, userDetails.getUsername()));
    }

    @Operation(summary = "Atualizar lista de favoritos", description = "Atualiza os dados de uma lista de favoritos existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de favoritos atualizada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Lista de favoritos não encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ListaFavoritosResponseDTO> atualizarLista(@PathVariable Long id,
    																@Valid @RequestBody ListaFavoritosRequestDTO request,
                                                                    @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(listaFavoritosService.atualizar(id, request, userDetails.getUsername()));
    }

    @Operation(summary = "Deletar lista de favoritos", description = "Remove uma lista de favoritos do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lista de favoritos removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Lista de favoritos não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ListaFavoritosResponseDTO> deletarLista(@PathVariable Long id) {
        listaFavoritosService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
