package org.serratec.serratecFlix.service;

import jakarta.transaction.Transactional;
import org.serratec.serratecFlix.RequestDTO.SerieRequestDTO;
import org.serratec.serratecFlix.ResponseDTO.SerieResponseDTO;
import org.serratec.serratecFlix.entity.Serie;
import org.serratec.serratecFlix.exception.ValorNaoEncontradoException;
import org.serratec.serratecFlix.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

    public List<SerieResponseDTO> findAll() {
        List<Serie> series = serieRepository.findAll();

        if (series.isEmpty()) {
            throw new ValorNaoEncontradoException("Não existem Séries cadastradas.");
        }

        List<SerieResponseDTO> serieDTO = new ArrayList<>();
        for (Serie serie : series) {
            serieDTO.add(new SerieResponseDTO(serie));
        }
        return serieDTO;
    }

    public SerieResponseDTO findById(Long id) {
        Serie serie = serieRepository.findById(id)
            .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos uma série com esse identificador."));

        return new SerieResponseDTO(serie);
    }

    @Transactional
    public SerieResponseDTO cadastrar( SerieRequestDTO serieDTO) {
        
        Serie serie = new Serie();
        serie.setTitulo(serieDTO.getTitulo());
        serie.setDescricao(serieDTO.getDescricao());
        serie.setTemporadas(serieDTO.getTemporadas());
        serie.setEpisodios(serieDTO.getEpisodios());
        serie.setDataLancamento(serieDTO.getDataLancamento());
        serie.setCategorias(serieDTO.getCategorias());
        
        serieRepository.save(serie);
    
        return new SerieResponseDTO(serie);
    }

    @Transactional
    public SerieResponseDTO atualizar( SerieRequestDTO serieDTO, Long id){
        Serie serie = serieRepository.findById(id)
             .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos uma Série com esse identificador."));
        
        serie.setTitulo(serieDTO.getTitulo());
        serie.setDescricao(serieDTO.getDescricao());
        serie.setTemporadas(serieDTO.getTemporadas());
        serie.setEpisodios(serieDTO.getEpisodios());
        serie.setDataLancamento(serieDTO.getDataLancamento());
        serie.setCategorias(serieDTO.getCategorias());
    
        serieRepository.save(serie);
        
        return new SerieResponseDTO(serie);
    }

    @Transactional
    public void deletar(Long id){
         serieRepository.findById(id)
            .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos uma Série com esse identificador."));
    
        serieRepository.deleteById(id);
    }
}


