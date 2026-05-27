package org.serratec.serratecFlix.service;

import org.serratec.serratecFlix.dto.requestdto.AvaliacaoAtualizacaoDTO;
import org.serratec.serratecFlix.dto.requestdto.AvaliacaoSerieRequestDTO;
import org.serratec.serratecFlix.dto.responsedto.AvaliacaoSerieResponseDTO;
import org.serratec.serratecFlix.entity.AvaliacaoSerie;
import org.serratec.serratecFlix.entity.Serie;
import org.serratec.serratecFlix.entity.Usuario;
import org.serratec.serratecFlix.enums.ClassificacaoIndicativa;
import org.serratec.serratecFlix.exception.IdadeInsuficienteException;
import org.serratec.serratecFlix.exception.ValorDuplicadoException;
import org.serratec.serratecFlix.exception.ValorNaoEncontradoException;
import org.serratec.serratecFlix.repository.AvaliacaoSerieRepository;
import org.serratec.serratecFlix.repository.SerieRepository;
import org.serratec.serratecFlix.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


@Service
public class AvaliacaoSerieService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AvaliacaoSerieRepository avaliacaoSerieRepository;

    @Autowired
    private SerieRepository serieRepository;
    
    @Autowired
    private ExperienciaService experienciaService;

    public List<AvaliacaoSerieResponseDTO> findAll() {
        List<AvaliacaoSerie> avaliacoes = avaliacaoSerieRepository.findAll();

        if (avaliacoes.isEmpty()) {
            throw new ValorDuplicadoException("Não existem Avaliações cadastradas.");
        }
        List<AvaliacaoSerieResponseDTO> avaliacaoSerieDTO = new ArrayList<>();

        for (AvaliacaoSerie avaliacaoSerie : avaliacoes) {
            avaliacaoSerieDTO.add(new AvaliacaoSerieResponseDTO(avaliacaoSerie));
        }
        return avaliacaoSerieDTO;
    }

    public AvaliacaoSerieResponseDTO findById(Long id) {
        AvaliacaoSerie avaliacoes = avaliacaoSerieRepository.findById(id)
                .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos uma Avaliação de Serie com esse identificador."));
        AvaliacaoSerieResponseDTO avaliacaoSerieDTO = new AvaliacaoSerieResponseDTO(avaliacoes);
        return avaliacaoSerieDTO;
    }

    public AvaliacaoSerieResponseDTO cadastrar(Long id, AvaliacaoSerieRequestDTO avaliacaoSerieRequest, String username) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new ValorNaoEncontradoException("Usuario não encontrado");
        }

        Serie serie = serieRepository.findById(id)
                .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos um Serie com esse identificador."));

        verificarIdade(usuario, serie.getTitulo(), serie.getClassificacao());

        AvaliacaoSerie avaliacaoSerie = new AvaliacaoSerie();
        avaliacaoSerie.setNota(avaliacaoSerieRequest.getNota());
        avaliacaoSerie.setComentario(avaliacaoSerieRequest.getComentario());
        avaliacaoSerie.setUsuario(usuario);
        avaliacaoSerie.setSerie(serie);
        
        //Experiencia
        
        experienciaService.atualizar(usuario, 2);

        return new AvaliacaoSerieResponseDTO(avaliacaoSerieRepository.save(avaliacaoSerie));
    }

    public AvaliacaoSerieResponseDTO atualizar(Long id, AvaliacaoAtualizacaoDTO avaliacaoSerieAtualizacao, String username) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new ValorNaoEncontradoException("Usuario não encontrado");
        }

        AvaliacaoSerie avaliacaoSerie = avaliacaoSerieRepository.findById(id)
                .orElseThrow(() -> new ValorNaoEncontradoException("Avaliação não encontrada."));

        if (avaliacaoSerie.getUsuario().getId() != usuario.getId()) {
            throw new ValorNaoEncontradoException("Você não tem permissão para atualizar esta avaliação de serie.");
        }

        avaliacaoSerie.setNota(avaliacaoSerieAtualizacao.getNota());
        avaliacaoSerie.setComentario(avaliacaoSerieAtualizacao.getComentario());

        return new AvaliacaoSerieResponseDTO(avaliacaoSerieRepository.save(avaliacaoSerie));
    }

    public void deletar(Long id) {
        avaliacaoSerieRepository.findById(id).orElseThrow(() -> new ValorNaoEncontradoException("Avaliação não encontrada."));
        avaliacaoSerieRepository.deleteById(id);
    }

    private void verificarIdade(Usuario usuario, String titulo, ClassificacaoIndicativa classificacao) {

        Integer idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();

        if (idade < classificacao.getIdadeMinima()) {
            throw new IdadeInsuficienteException(titulo, classificacao.getIdadeMinima());
        }
    }
}
