package org.serratec.serratecFlix.controller;

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

@RestController
@RequestMapping("/historicos")
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;

    @GetMapping("/filmes")
    public ResponseEntity<List<HistoricoResponseDTO>> listarFilmes(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(historicoService.listarFilmes(userDetails.getUsername()));
    }

    @GetMapping("/series")
    public ResponseEntity<List<HistoricoResponseDTO>> listarSeries(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(historicoService.listarSeries(userDetails.getUsername()));
    }

    @PostMapping
    public ResponseEntity<HistoricoResponseDTO> salvarHistorico(@Valid @RequestBody HistoricoRequestDTO request, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(201).body(historicoService.salvar(request, userDetails.getUsername()));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<HistoricoResponseDTO> atualizarStatus(@PathVariable Long id, @RequestParam StatusAssistido novoStatus,
                                                                @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(historicoService.atualizarStatus(id, novoStatus, userDetails.getUsername()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarHistorico(@PathVariable Long id) {
        historicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
