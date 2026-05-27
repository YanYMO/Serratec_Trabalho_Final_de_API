package org.serratec.serratecFlix.service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.serratecFlix.dto.responsedto.FilmeResponseDTO;
import org.serratec.serratecFlix.dto.responsedto.SerieResponseDTO;
import org.serratec.serratecFlix.entity.Filme;
import org.serratec.serratecFlix.entity.Serie;
import org.serratec.serratecFlix.exception.ValorNaoEncontradoException;
import org.serratec.serratecFlix.repository.AvaliacaoFilmeRepository;
import org.serratec.serratecFlix.repository.AvaliacaoSerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankingService {

    @Autowired
    private AvaliacaoFilmeRepository avaliacaoFilmeRepository; // <- precisa desse

    @Autowired
    private AvaliacaoSerieRepository avaliacaoSerieRepository; // <- e desse

    public List<FilmeResponseDTO> top5Filmes() {
        List<Filme> filmes = avaliacaoFilmeRepository.top5Filmes(); // <- minúsculo

        if (filmes.isEmpty()) {
            throw new ValorNaoEncontradoException("Nenhum filme encontrado.");
        }

        List<FilmeResponseDTO> top5 = new ArrayList<>();
        for (Filme filme : filmes) {
            top5.add(new FilmeResponseDTO(filme));
        }
        return top5;
    }

    public List<SerieResponseDTO> top5Series() {
        List<Serie> series = avaliacaoSerieRepository.top5Series(); // <- minúsculo

        if (series.isEmpty()) {
            throw new ValorNaoEncontradoException("Nenhuma série encontrada.");
        }

        List<SerieResponseDTO> top5 = new ArrayList<>();
        for (Serie serie : series) {
            top5.add(new SerieResponseDTO(serie));
        }
        return top5;
    }
}