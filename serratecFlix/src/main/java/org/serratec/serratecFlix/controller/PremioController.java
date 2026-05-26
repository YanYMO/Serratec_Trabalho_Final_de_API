package org.serratec.serratecFlix.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.serratec.serratecFlix.dto.requestdto.PremioRequestDTO;
import org.serratec.serratecFlix.dto.responsedto.PremioResponseDTO;
import org.serratec.serratecFlix.service.PremioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

@Tag(name = "Prêmio", description = "Cadastro de prêmios")
@RestController
@RequestMapping("/premios")
@Validated
public class PremioController {

    @Autowired
    private PremioService premioService;

    @Operation(summary = "Cadastrar prêmio", description = "Cadastra um novo prêmio no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Prêmio cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<PremioResponseDTO> inserir(@RequestBody @Valid PremioRequestDTO premioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(premioService.inserir(premioDTO));
    }

    @Operation(summary = "Listar todos os prêmios", description = "Retorna uma lista com todos os prêmios cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de prêmios retornada com sucesso!")
    })
    @GetMapping
    public ResponseEntity<List<PremioResponseDTO>> buscarTodos() {
        return ResponseEntity.ok(premioService.buscarTodos());
    }

    @Operation(summary = "Buscar prêmio por ID", description = "Retorna um prêmio específica pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prêmio encontrado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Prêmio não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PremioResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(premioService.buscarPorId(id));
    }

    @Operation(summary = "Buscar prêmio por filme", description = "Retorna um prêmio específico pelo filme")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prêmio encontrado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Prêmio não encontrado")
    })
    @GetMapping("/filme/{filmeId}")
    public ResponseEntity<List<PremioResponseDTO>> buscarPorFilme(@PathVariable Long idFilme) {
        return ResponseEntity.ok(premioService.buscarPorFilme(idFilme));
    }

    @Operation(summary = "Buscar prêmio por série", description = "Retorna um prêmio específico pela série")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prêmio encontrado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Prêmio não encontrado")
    })
    @GetMapping("/serie/{serieId}")
    public ResponseEntity<List<PremioResponseDTO>> buscarPorSerie(@PathVariable Long idSerie) {
        return ResponseEntity.ok(premioService.buscarPorSerie(idSerie));
    }

    @Operation(summary = "Buscar prêmio por ano", description = "Retorna um prêmio específico pelo ano")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prêmio encontrado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Prêmio não encontrado")
    })
    @GetMapping("/ano/{ano}")
    public ResponseEntity<List<PremioResponseDTO>> buscarPorAno(@PathVariable Integer ano) {
        return ResponseEntity.ok(premioService.buscarPorAno(ano));
    }

    @Operation(summary = "Atualizar prêmio", description = "Atualiza os dados de um prêmio existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prêmio atualizado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Prêmio não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PremioResponseDTO> atualizar( @PathVariable Long id,@RequestBody @Valid PremioRequestDTO premioDTO) {
        return ResponseEntity.ok(premioService.atualizar(id, premioDTO));
    }

    @Operation(summary = "Deletar prêmio", description = "Remove um prêmio do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Prêmio removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Prêmio não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        premioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
