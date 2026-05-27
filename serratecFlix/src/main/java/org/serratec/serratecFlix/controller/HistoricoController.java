package org.serratec.serratecFlix.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.serratec.serratecFlix.dto.requestdto.HistoricoRequestDTO;
import org.serratec.serratecFlix.dto.responsedto.HistoricoResponseDTO;
import org.serratec.serratecFlix.enums.StatusAssistido;
import org.serratec.serratecFlix.service.HistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Histórico de exibição", description = "Cadastro de históricos de filmes ou séries")
@RestController
@RequestMapping("/historicos")
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;

    @Operation(summary = "Listar todo o histórico de filmes", description = "Retorna uma lista com o histórico de filmes cadastrados")
    @GetMapping("/filmes")
    public ResponseEntity<List<HistoricoResponseDTO>> listarFilmes(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(historicoService.listarFilmes(userDetails.getUsername()));
    }

    @Operation(summary = "Listar todo o histórico de séries", description = "Retorna uma lista com o histórico de séries cadastradas")
    @GetMapping("/series")
    public ResponseEntity<List<HistoricoResponseDTO>> listarSeries(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(historicoService.listarSeries(userDetails.getUsername()));
    }

    @Operation(summary = "Cadastrar histórico de filmes ou séries", description = "Cadastra um novo histórico de filmes ou séries no sistema")
    @PostMapping
    public ResponseEntity<HistoricoResponseDTO> salvarHistorico(@Valid @RequestBody HistoricoRequestDTO request, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(201).body(historicoService.salvar(request, userDetails.getUsername()));
    }

    @Operation(summary = "Atualizar status do histórico", description = "Atualiza o status de exibição do histórico")
    @PutMapping("/{id}/status")
    public ResponseEntity<HistoricoResponseDTO> atualizarStatus(@PathVariable Long id, @RequestParam StatusAssistido novoStatus,
                                                                @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(historicoService.atualizarStatus(id, novoStatus, userDetails.getUsername()));
    }

    @Operation(summary = "Deletar histórico de filmes ou séries", description = "Remove um histórico de filme ou série do sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarHistorico(@PathVariable Long id) {
        historicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
