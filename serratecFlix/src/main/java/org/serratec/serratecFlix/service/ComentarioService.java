package org.serratec.serratecFlix.service;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.serratec.serratecFlix.RequestDTO.ComentarioRequestDTO;
import org.serratec.serratecFlix.ResponseDTO.ComentarioResponseDTO;
import org.serratec.serratecFlix.entity.Comentario;
import org.serratec.serratecFlix.entity.Filme;
import org.serratec.serratecFlix.entity.Serie;
import org.serratec.serratecFlix.entity.Usuario;
import org.serratec.serratecFlix.exception.ValorNaoEncontradoException;
import org.serratec.serratecFlix.repository.ComentarioRepository;
import org.serratec.serratecFlix.repository.FilmeRepository;
import org.serratec.serratecFlix.repository.SerieRepository;
import org.serratec.serratecFlix.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ComentarioResponseDTO inserir(ComentarioRequestDTO comentarioDTO) {
        if (comentarioDTO.getFilmeId() == null && comentarioDTO.getSerieId() == null) {
            throw new ValorNaoEncontradoException("Informe um filme ou série válidos");
        }

        Usuario usuario = usuarioRepository.findById(comentarioDTO.getUsuarioId())
                .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos nenhum usuário com esse identificador"));

        Comentario comentario = new Comentario();
        comentario.setConteudo(comentarioDTO.getConteudo());
        comentario.setUsuario(usuario);
        comentario.setDataCriacao(LocalDateTime.now());

        if (comentarioDTO.getFilmeId() != null) {
            Filme filme = filmeRepository.findById(comentarioDTO.getFilmeId())
                    .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos nenhum Filme com esse identificador"));
            comentario.setFilme(filme);
        }

        if (comentarioDTO.getSerieId() != null) {
            Serie serie = serieRepository.findById(comentarioDTO.getSerieId())
                    .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos nenhuma Série com esse identificador"));
            comentario.setSerie(serie);
        }

        return toResponseDTO(comentarioRepository.save(comentario));
    }

    public List<ComentarioResponseDTO> buscarPorFilme(Long idFilme) {
        return comentarioRepository.findByFilmeIdOrderByDataCriacaoDesc(idFilme)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public List<ComentarioResponseDTO> buscarPorSerie(Long idSerie) {
        return comentarioRepository.findBySerieIdOrderByDataCriacaoDesc(idSerie)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public List<ComentarioResponseDTO> buscarPorUsuario(Long idUsuario) {
        return comentarioRepository.buscarPorUsuario(idUsuario)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }


    public ComentarioResponseDTO atualizar(Long id, ComentarioRequestDTO comentarioDTO) {
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos nenhum comentário com esse identificador"));

        comentario.setConteudo(comentarioDTO.getConteudo());

        return toResponseDTO(comentarioRepository.save(comentario));
    }

    public void deletar(Long id) {
        if (!comentarioRepository.existsById(id)) {
            throw new ValorNaoEncontradoException("Não encontramos nenhum comentário com esse identificador");
        }
        comentarioRepository.deleteById(id);
    }

    private ComentarioResponseDTO toResponseDTO(Comentario comentario) {
        ComentarioResponseDTO comentarioDTO = modelMapper.map(comentario, ComentarioResponseDTO.class);
        comentarioDTO.setNomeUsuario(comentario.getUsuario().getNome());

        if (comentario.getFilme() != null) {
            comentarioDTO.setNomeConteudo(comentario.getFilme().getTitulo());
            comentarioDTO.setTipoConteudo("FILME");
        } else {
            comentarioDTO.setNomeConteudo(comentario.getSerie().getTitulo());
            comentarioDTO.setTipoConteudo("SERIE");
        }

        return comentarioDTO;
    }
}
