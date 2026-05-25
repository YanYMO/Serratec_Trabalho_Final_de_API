package org.serratec.serratecFlix.service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.serratecFlix.DTO.RequestDTO.UsuarioRequestDTO;
import org.serratec.serratecFlix.DTO.ResponseDTO.UsuarioResponseDTO;
import org.serratec.serratecFlix.entity.Usuario;
import org.serratec.serratecFlix.exception.ValorDuplicadoException;
import org.serratec.serratecFlix.exception.ValorNaoEncontradoException;
import org.serratec.serratecFlix.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public List<UsuarioResponseDTO> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        if (usuarios.isEmpty()) {
            throw new ValorDuplicadoException("Não existem Usuários cadastrados.");
        }
        List<UsuarioResponseDTO> usuarioDTO = new ArrayList<UsuarioResponseDTO>();

        for (Usuario usuario : usuarios) {
            usuarioDTO.add(new UsuarioResponseDTO(usuario));
        }
        return usuarioDTO;
    }

    public UsuarioResponseDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos um Usuário com esse identificador."));

        UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO(usuario);

        return usuarioDTO;
    }

    @Transactional
    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO usuarioDTO) {

        Usuario usuario = new Usuario();
        usuario.setDataNascimento(usuarioDTO.getDataNascimento());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setUsername(usuarioDTO.getUserName());
        usuario.setSenha(encoder.encode(usuarioDTO.getSenha()));

        usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(usuario);
    }

    @Transactional
    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos um Usuário com esse identificador."));

        usuario.setDataNascimento(usuarioDTO.getDataNascimento());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setUsername(usuarioDTO.getUserName());
        usuario.setSenha(usuarioDTO.getSenha());

        usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(usuario);
    }

    @Transactional
    public void deletar(Long id) {
         usuarioRepository.findById(id)
                .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos um Usuário com esse identificador."));

        usuarioRepository.deleteById(id);
    }
}
