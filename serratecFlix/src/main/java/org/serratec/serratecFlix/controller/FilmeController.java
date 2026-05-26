package org.serratec.serratecFlix.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.serratec.serratecFlix.dto.requestdto.FilmeRequestDTO;
import org.serratec.serratecFlix.dto.responsedto.FilmeResponseDTO;
import org.serratec.serratecFlix.dto.responsedto.OmdbFilmeResponseDTO;
import org.serratec.serratecFlix.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Filme", description = "Cadastro de filmes")
@RestController
@RequestMapping("/filmes")
@Validated
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @Operation(summary = "Listar todos os filmes", description = "Retorna uma lista com todos os filmes cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de filmes retornada com sucesso!")
    })
    @GetMapping
    public ResponseEntity<List<FilmeResponseDTO>> listarFilmes() {
        return ResponseEntity.ok(filmeService.findAll());
    }

    @Operation(summary = "Buscar filme por ID", description = "Retorna um filme específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filme encontrado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FilmeResponseDTO> listarPorId(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(filmeService.findById(id, userDetails.getUsername()));
    }

    @Operation(summary = "Cadastrar filme", description = "Cadastra um novo filme no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Filme cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<FilmeResponseDTO> criarFilme(@Valid @RequestBody FilmeRequestDTO request) {
        return ResponseEntity.status(201).body(filmeService.cadastrar(request));
    }

    @Operation(summary = "Atualizar filme", description = "Atualiza os dados de um filme existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filme atualizado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<FilmeResponseDTO> atualizarFilme(@PathVariable Long id,
    													   @Valid @RequestBody FilmeRequestDTO request) {
        return ResponseEntity.ok(filmeService.atualizar(id, request));
    }

    @Operation(summary = "Deletar filme", description = "Remove um filme do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Filme removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<FilmeResponseDTO> deletarFilme(@PathVariable Long id) {
        filmeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/externo")
    @Operation(summary = "Buscar filme em API externa", description = "Busca informações de um filme na API do OMDB pelo título")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso! (campos podem ser nulos caso o filme não seja encontrado)"),
        @ApiResponse(responseCode = "400", description = "Título inválido ou não informado")
    })
    public ResponseEntity<OmdbFilmeResponseDTO> buscarExterno(@RequestParam String titulo) {
    return ResponseEntity.ok(filmeService.buscarApiExterna(titulo));
}
}
