package org.serratec.serratecFlix.service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.serratecFlix.dto.requestdto.AvaliacaoSerieRequestDTO;
import org.serratec.serratecFlix.dto.responsedto.AvaliacaoSerieResponseDTO;
import org.serratec.serratecFlix.entity.AvaliacaoSerie;
import org.serratec.serratecFlix.entity.Serie;
import org.serratec.serratecFlix.entity.Usuario;
import org.serratec.serratecFlix.exception.ValorDuplicadoException;
import org.serratec.serratecFlix.exception.ValorNaoEncontradoException;
import org.serratec.serratecFlix.repository.AvaliacaoSerieRepository;
import org.serratec.serratecFlix.repository.SerieRepository;
import org.serratec.serratecFlix.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoSerieService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AvaliacaoSerieRepository avaliacaoSerieRepository;

    @Autowired
    private SerieRepository serieRepository;

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

    public AvaliacaoSerieResponseDTO cadastrar(AvaliacaoSerieRequestDTO avaliacaoSerieRequest) {
        Usuario usuario = usuarioRepository.findById(avaliacaoSerieRequest.getUsuarioId())
                .orElseThrow(() -> new ValorNaoEncontradoException("Usuário não encontrado."));

        Serie serie = serieRepository.findById(avaliacaoSerieRequest.getSerieId())
                .orElseThrow(() -> new ValorNaoEncontradoException("Série não encontrada."));

        AvaliacaoSerie avaliacaoSerie = new AvaliacaoSerie();
        avaliacaoSerie.setNota(avaliacaoSerieRequest.getNota());
        avaliacaoSerie.setComentario(avaliacaoSerieRequest.getComentario());
        avaliacaoSerie.setUsuario(usuario);
        avaliacaoSerie.setSerie(serie);

        AvaliacaoSerieResponseDTO response = new AvaliacaoSerieResponseDTO(avaliacaoSerieRepository.save(avaliacaoSerie));
        atualizarMediaSerie(serie);
        return response;
    }

    public AvaliacaoSerieResponseDTO atualizar(Long id, AvaliacaoSerieRequestDTO avaliacaoSerieAtualizacao) {
        AvaliacaoSerie avaliacaoSerie = avaliacaoSerieRepository.findById(id)
                .orElseThrow(() -> new ValorNaoEncontradoException("Avaliação não encontrada."));
        avaliacaoSerie.setNota(avaliacaoSerieAtualizacao.getNota());
        avaliacaoSerie.setComentario(avaliacaoSerieAtualizacao.getComentario());

        AvaliacaoSerieResponseDTO response = new AvaliacaoSerieResponseDTO(avaliacaoSerieRepository.save(avaliacaoSerie));
        atualizarMediaSerie(avaliacaoSerie.getSerie());
        return response;
    }

    public void deletar(Long id) {
        AvaliacaoSerie avaliacaoSerie = avaliacaoSerieRepository.findById(id)
                .orElseThrow(() -> new ValorNaoEncontradoException("Avaliação não encontrada."));
        Serie serie = avaliacaoSerie.getSerie();
        avaliacaoSerieRepository.deleteById(id);
        atualizarMediaSerie(serie);
    }

    private void atualizarMediaSerie(Serie serie) {
        List<AvaliacaoSerie> avaliacoes = avaliacaoSerieRepository.findBySerie(serie);

        if (avaliacoes.isEmpty()) {
            serie.setNotaMedia(0.0);
        } else {
            int soma = 0;
            for (AvaliacaoSerie avaliacao : avaliacoes) {
                soma += avaliacao.getNota();
            }
            double media = (double) soma / avaliacoes.size();
            serie.setNotaMedia(media);
        }
        serieRepository.save(serie);
    }
}