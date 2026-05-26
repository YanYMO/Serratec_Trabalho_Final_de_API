package org.serratec.serratecFlix.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.serratec.serratecFlix.dto.requestdto.SerieRequestDTO;
import org.serratec.serratecFlix.dto.responsedto.SerieResponseDTO;
import org.serratec.serratecFlix.service.SerieService;
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

@Tag(name = "Série", description = "Cadastro de séries")
@RestController
@RequestMapping("/series")
@Validated
public class SerieController {
    
    @Autowired
    private SerieService serieService;

    @Operation(summary = "Listar todos as séries", description = "Retorna uma lista com todas as séries cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de séries retornada com sucesso!")
    })
    @GetMapping
    public ResponseEntity<List<SerieResponseDTO>> listarSeries() {
        return ResponseEntity.ok(serieService.findAll());
    }

    @Operation(summary = "Buscar série por ID", description = "Retorna uma série específica pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Série encontrada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Série não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SerieResponseDTO> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(serieService.findById(id));
    }

    @Operation(summary = "Cadastrar série", description = "Cadastra uma nova série no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Série cadastrada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<SerieResponseDTO> criarSerie(@Valid @RequestBody SerieRequestDTO request) {
        return ResponseEntity.status(201).body(serieService.cadastrar(request));
    }

    @Operation(summary = "Atualizar série", description = "Atualiza os dados de uma série existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Série atualizada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Série não encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SerieResponseDTO> atualizarSerie(@PathVariable Long id,
    													   @Valid @RequestBody SerieRequestDTO request) {
        return ResponseEntity.ok(serieService.atualizar(id, request));
    }

    @Operation(summary = "Deletar série", description = "Remove uma série do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Série removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Série não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<SerieResponseDTO> deletarSerie(@PathVariable Long id) {
        serieService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
