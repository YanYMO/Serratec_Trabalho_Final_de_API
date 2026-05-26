package org.serratec.serratecFlix.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.serratec.serratecFlix.dto.requestdto.UsuarioRequestDTO;
import org.serratec.serratecFlix.dto.responsedto.UsuarioResponseDTO;
import org.serratec.serratecFlix.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
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

@Tag(name = "Usuário", description = "Cadastro de usuários")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Listar todos as usuários", description = "Retorna uma lista com todos as usuários cadastradas")
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário específico pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @Operation(summary = "Cadastrar usuário", description = "Cadastra um novo usuário no sistema")
    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(@Valid @RequestBody UsuarioRequestDTO request) {
        return ResponseEntity.status(201).body(usuarioService.cadastrar(request));
    }

    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário existente")
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable Long id,
    														   @Valid @RequestBody UsuarioRequestDTO request) {
        return ResponseEntity.ok(usuarioService.atualizar(id, request));
    }

    @Operation(summary = "Deletar usuário", description = "Remove um usuário do sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
