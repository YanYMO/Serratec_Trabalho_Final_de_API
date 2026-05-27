package org.serratec.serratecFlix.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.serratec.serratecFlix.dto.requestdto.AvaliacaoAtualizacaoDTO;
import org.serratec.serratecFlix.dto.requestdto.AvalicaoFilmeRequestDTO;
import org.serratec.serratecFlix.dto.responsedto.AvaliacaoFilmeResponseDTO;
import org.serratec.serratecFlix.service.AvaliacaoFilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

@Tag(name = "Avaliação de Filmes", description = "Cadastro de avaliações de filmes")
@RestController
@RequestMapping("/avaliacoes-filmes")
@Validated
public class AvaliacaoFilmeController {
    
    @Autowired
    private AvaliacaoFilmeService avaliacaoFilmeService;

    @Operation(summary = "Listar todas as avaliações de filme", description = "Retorna uma lista com todas as avaliações de filme cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de avaliações de filme retornada com sucesso!")
    })
    @GetMapping
    public ResponseEntity<List<AvaliacaoFilmeResponseDTO>> listarAvaliacoes() {
        return ResponseEntity.ok(avaliacaoFilmeService.findAll());
    }

    @Operation(summary = "Buscar avaliação de filme por ID", description = "Retorna uma avaliação de filme específica pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Avaliação de filme encontrada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Avaliação de filme não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoFilmeResponseDTO> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(avaliacaoFilmeService.findById(id));
    }

    @Operation(summary = "Cadastrar avaliação de filme", description = "Cadastra uma nova avaliação de filme no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Avaliação de filme cadastrada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/{id}")
    public ResponseEntity<AvaliacaoFilmeResponseDTO> criarAvaliacao(@PathVariable Long id,
                                                                    @Valid @RequestBody AvalicaoFilmeRequestDTO request,
                                                                    @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(201).body(avaliacaoFilmeService.cadastrar(id, request, userDetails.getUsername()));
    }

    @Operation(summary = "Atualizar avaliação de filme", description = "Atualiza os dados de uma avaliação de filme existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Avaliação de filme atualizada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Avaliação de filme não encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoFilmeResponseDTO> atualizarAvaliacao(@PathVariable Long id,
    																	@Valid @RequestBody AvaliacaoAtualizacaoDTO request,
                                                                        @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(avaliacaoFilmeService.atualizar(id, request, userDetails.getUsername()));
    }

    @Operation(summary = "Deletar avaliação de filme", description = "Remove uma avaliação de filme do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Avaliação de filme removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Avaliação de filme não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<AvaliacaoFilmeResponseDTO> deletarAvaliacao(@PathVariable Long id) {
        avaliacaoFilmeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
