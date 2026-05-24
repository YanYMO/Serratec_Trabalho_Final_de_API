package org.serratec.serratecFlix.controller;

import java.util.List;

import org.serratec.serratecFlix.DTO.RequestDTO.PremioRequestDTO;
import org.serratec.serratecFlix.DTO.ResponseDTO.PremioResponseDTO;
import org.serratec.serratecFlix.service.PremioService;
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
@RequestMapping("/premios")
public class PremioController {

    @Autowired
    private PremioService premioService;

    @PostMapping
    public ResponseEntity<PremioResponseDTO> inserir(@RequestBody @Valid PremioRequestDTO premioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(premioService.inserir(premioDTO));
    }

    @GetMapping
    public ResponseEntity<List<PremioResponseDTO>> buscarTodos() {
        return ResponseEntity.ok(premioService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PremioResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(premioService.buscarPorId(id));
    }

    @GetMapping("/filme/{filmeId}")
    public ResponseEntity<List<PremioResponseDTO>> buscarPorFilme(@PathVariable Long idFilme) {
        return ResponseEntity.ok(premioService.buscarPorFilme(idFilme));
    }

    @GetMapping("/serie/{serieId}")
    public ResponseEntity<List<PremioResponseDTO>> buscarPorSerie(@PathVariable Long idSerie) {
        return ResponseEntity.ok(premioService.buscarPorSerie(idSerie));
    }


    @GetMapping("/ano/{ano}")
    public ResponseEntity<List<PremioResponseDTO>> buscarPorAno(@PathVariable Integer ano) {
        return ResponseEntity.ok(premioService.buscarPorAno(ano));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PremioResponseDTO> atualizar( @PathVariable Long id,@RequestBody @Valid PremioRequestDTO premioDTO) {
        return ResponseEntity.ok(premioService.atualizar(id, premioDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        premioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
