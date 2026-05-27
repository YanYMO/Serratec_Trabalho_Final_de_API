package org.serratec.serratecFlix.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.serratec.serratecFlix.dto.requestdto.AvaliacaoAtualizacaoDTO;
import org.serratec.serratecFlix.dto.requestdto.AvaliacaoSerieRequestDTO;
import org.serratec.serratecFlix.dto.responsedto.AvaliacaoSerieResponseDTO;
import org.serratec.serratecFlix.service.AvaliacaoSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Avaliação de Séries", description = "Cadastro de avaliações de séries")
@RestController
@RequestMapping("/avaliacoes-series")
@Validated
public class AvaliacaoSerieController {
    
    @Autowired
    private AvaliacaoSerieService avaliacaoSerieService;

    @Operation(summary = "Listar todas as avaliações de série", description = "Retorna uma lista com todos as avaliações de série cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de avaliações de série retornada com sucesso!")
    })
    @GetMapping
    public ResponseEntity<List<AvaliacaoSerieResponseDTO>> listarAvaliacoes() {
        return ResponseEntity.ok(avaliacaoSerieService.findAll());
    }

    @Operation(summary = "Buscar avaliação de série por ID", description = "Retorna uma avaliação de série específica pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Avaliação de série encontrada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Avaliação de série não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoSerieResponseDTO> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(avaliacaoSerieService.findById(id));
    }

    @Operation(summary = "Cadastrar avaliação de série", description = "Cadastra uma nova avaliação de série no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Avaliação de série cadastrada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/{id}")
    public ResponseEntity<AvaliacaoSerieResponseDTO> criarAvaliacao(@PathVariable Long id,
                                                                    @Valid @RequestBody AvaliacaoSerieRequestDTO request,
                                                                    @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(201).body(avaliacaoSerieService.cadastrar(id, request, userDetails.getUsername()));
    }

    @Operation(summary = "Atualizar avaliação de série", description = "Atualiza os dados de uma avaliação de série existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Avaliação de série atualizada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Avaliação de série não encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoSerieResponseDTO> atualizarAvaliacao(@PathVariable Long id,
    																	@Valid @RequestBody AvaliacaoAtualizacaoDTO request,
                                                                        @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(avaliacaoSerieService.atualizar(id, request, userDetails.getUsername()));
    }

    @Operation(summary = "Deletar avaliação de série", description = "Remove uma avaliação de série do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Avaliação de série removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Avaliação de série não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<AvaliacaoSerieResponseDTO> deletarAvaliacao(@PathVariable Long id) {
        avaliacaoSerieService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
