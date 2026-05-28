package org.serratec.serratecFlix.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.serratec.serratecFlix.dto.requestdto.HistoricoRequestDTO;
import org.serratec.serratecFlix.dto.responsedto.HistoricoCompletoResponseDTO;
import org.serratec.serratecFlix.dto.responsedto.HistoricoResponseDTO;
import org.serratec.serratecFlix.dto.responsedto.HistoricoResumoResponseDTO;
import org.serratec.serratecFlix.entity.Filme;
import org.serratec.serratecFlix.entity.HistoricoAssistido;
import org.serratec.serratecFlix.entity.Serie;
import org.serratec.serratecFlix.entity.Usuario;
import org.serratec.serratecFlix.enums.ClassificacaoIndicativa;
import org.serratec.serratecFlix.enums.StatusAssistido;
import org.serratec.serratecFlix.exception.IdadeInsuficienteException;
import org.serratec.serratecFlix.exception.ValorDuplicadoException;
import org.serratec.serratecFlix.exception.ValorNaoEncontradoException;
import org.serratec.serratecFlix.repository.FilmeRepository;
import org.serratec.serratecFlix.repository.HistoricoAssistidoRepository;
import org.serratec.serratecFlix.repository.SerieRepository;
import org.serratec.serratecFlix.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

    public HistoricoResumoResponseDTO resumo(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if(usuario==null) {
            throw new ValorNaoEncontradoException("Usuário não foi econtrado");
        }
        List<HistoricoAssistido> listaHistorico = historicoAssistidoRepository.findByUsuarioId(usuario.getId());
        long totalFilmes = listaHistorico.stream().filter(h -> h.getFilme() != null).count();
        long totalSeries = listaHistorico.stream().filter(h -> h.getSerie() != null).count();
        long assistindo = listaHistorico.stream().filter(h -> h.getStatusAssistido() == StatusAssistido.ASSISTINDO).count();
        long pausado = listaHistorico.stream().filter(h -> h.getStatusAssistido() == StatusAssistido.PAUSADO).count();
        long assistido = listaHistorico.stream().filter(h -> h.getStatusAssistido() == StatusAssistido.ASSISTIDO).count();
        return new HistoricoResumoResponseDTO(totalFilmes, totalSeries, assistindo, pausado, assistido);
    }

    public HistoricoCompletoResponseDTO listarTudo(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if(usuario==null) {
            throw new ValorNaoEncontradoException("Usuário não foi econtrado");
        }
        return new HistoricoCompletoResponseDTO(
                usuario.getNome(),
                usuario.getEmail(),
                listarFilmes(username),
                listarSeries(username));
    }

    public HistoricoResponseDTO salvar(HistoricoRequestDTO request, String username) {
        if (request.getIdFilme() == null && request.getIdSerie() == null)
            throw new ValorNaoEncontradoException("Informe idFilme ou idSerie");

        if (request.getIdFilme() != null && request.getIdSerie() != null)
            throw new ValorDuplicadoException("Preencha apenas um: idFilme ou idSerie");

        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null)
            throw new ValorNaoEncontradoException("Usuário não foi encontrado");

        HistoricoAssistido historico = new HistoricoAssistido();
        historico.setUsuario(usuario);
        historico.setStatusAssistido(request.getStatus());
        historico.setData(LocalDateTime.now());

        if (request.getIdFilme() != null) {
            Filme filme = filmeRepository.findById(request.getIdFilme()).orElseThrow(() -> new ValorNaoEncontradoException("Filme não encontrado"));
            verificarIdade(usuario, filme.getTitulo(), filme.getClassificacao());
            historico.setFilme(filme);
        } else if(request.getIdSerie() != null) {
            Serie serie = serieRepository.findById(request.getIdSerie()).orElseThrow(() -> new ValorNaoEncontradoException("Série não encontrada"));
            verificarIdade(usuario, serie.getTitulo(), serie.getClassificacao());
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
        historico.setData(LocalDateTime.now());
        return new HistoricoResponseDTO(historicoAssistidoRepository.save(historico));
    }

    public void deletar(Long id) {
        historicoAssistidoRepository.findById(id).orElseThrow(() -> new ValorNaoEncontradoException("Histórico não foi encontrado"));
        historicoAssistidoRepository.deleteById(id);
    }

    private void verificarIdade(Usuario usuario, String titulo, ClassificacaoIndicativa classificacao) {

        Integer idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();

        if (idade < classificacao.getIdadeMinima()) {
            throw new IdadeInsuficienteException(titulo, classificacao.getIdadeMinima());
        }
    }
}
