package org.serratec.serratecFlix.service;

import org.serratec.serratecFlix.dto.requestdto.HistoricoRequestDTO;
import org.serratec.serratecFlix.dto.responsedto.HistoricoResponseDTO;
import org.serratec.serratecFlix.entity.Filme;
import org.serratec.serratecFlix.entity.HistoricoAssistido;
import org.serratec.serratecFlix.entity.Serie;
import org.serratec.serratecFlix.entity.Usuario;
import org.serratec.serratecFlix.enums.StatusAssistido;
import org.serratec.serratecFlix.exception.ValorNaoEncontradoException;
import org.serratec.serratecFlix.repository.FilmeRepository;
import org.serratec.serratecFlix.repository.HistoricoAssistidoRepository;
import org.serratec.serratecFlix.repository.SerieRepository;
import org.serratec.serratecFlix.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoAssistidoRepository historicoAssistidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private SerieRepository serieRepository;

    public List<HistoricoResponseDTO> listarFilmes(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if(usuario==null) {
            throw new ValorNaoEncontradoException("Usuário não foi econtrado!");
        }
        return historicoAssistidoRepository.findByUsuarioId(usuario.getId())
                .stream().filter(h -> h.getFilme() != null)
                .map(h -> new HistoricoResponseDTO(h))
                .collect(Collectors.toList());
    }

    public List<HistoricoResponseDTO> listarSeries(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if(usuario==null) {
            throw new ValorNaoEncontradoException("Usuário não foi econtrado!");
        }
        return historicoAssistidoRepository.findByUsuarioId(usuario.getId())
                .stream().filter(h -> h.getSerie() != null)
                .map(h -> new HistoricoResponseDTO(h))
                .collect(Collectors.toList());
    }

    public HistoricoResponseDTO salvar(HistoricoRequestDTO request, String username) {
        if (request.getIdFilme() == null && request.getIdSerie() == null)
            throw new RuntimeException("Informe idFilme ou idSerie");

        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null)
            throw new ValorNaoEncontradoException("Usuário não foi encontrado");

        HistoricoAssistido historico = new HistoricoAssistido();
        historico.setUsuario(usuario);
        historico.setStatusAssistido(request.getStatus());
        historico.setData(LocalDate.now());

        if (request.getIdFilme() != null) {
            Filme filme = filmeRepository.findById(request.getIdFilme()).orElseThrow(() -> new ValorNaoEncontradoException("Filme não encontrado"));
            historico.setFilme(filme);
        } else {
            Serie serie = serieRepository.findById(request.getIdSerie()).orElseThrow(() -> new ValorNaoEncontradoException("Série não encontrada"));
            historico.setSerie(serie);
        }

        return new HistoricoResponseDTO(historicoAssistidoRepository.save(historico));
    }

    public HistoricoResponseDTO atualizarStatus(Long id, StatusAssistido novoStatus, String username) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null)
            throw new ValorNaoEncontradoException("Usuário não foi encontrado");

        HistoricoAssistido historico = historicoAssistidoRepository.findById(id).orElseThrow(() -> new ValorNaoEncontradoException("Histórico não foi encontrado"));

        historico.setStatusAssistido(novoStatus);
        historico.setData(LocalDate.now());
        return new HistoricoResponseDTO(historicoAssistidoRepository.save(historico));
    }

    public void deletar(Long id) {
        historicoAssistidoRepository.findById(id).orElseThrow(() -> new ValorNaoEncontradoException("Histórico não foi encontrado"));
        historicoAssistidoRepository.deleteById(id);
    }
}
