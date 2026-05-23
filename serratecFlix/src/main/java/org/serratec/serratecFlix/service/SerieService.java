package org.serratec.serratecFlix.service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.serratecFlix.entity.AvaliacaoSerie;
import org.serratec.serratecFlix.entity.Categoria;
import org.serratec.serratecFlix.entity.Serie;
import org.serratec.serratecFlix.exception.ValorNaoEncontradoException;
import org.serratec.serratecFlix.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class SerieService {
      
    @Autowired
    private SerieRepository serieRepository;

    public List<SerieResponseDTO> findAll(){
        List<Serie> series = serieRepository.findAll();
    }

    if(series.isEmpty()){
        throw new ValorNaoEncontradoException("Não existem Séries cadastradas.");
    }
    List<SerieResponseDTO> serieDTO = new ArrayList<SerieResponseDTO>();
    
    for (Serie serie : series){
        serieDTO.add(new SerieResponseDTO(serie));
    }
    return serieDTO;
}

public SerieResponse findById (Long id){
    Serie serie = serieRepository.findById(id)
          .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos uma série com esse identificador."));

    SerieResponse serieDTO = new UsuarioResponseDTO(usuario);

    return serieDTO;
}

@Transactional
public SerieResponseDTO cadastrar(@Valid SerieRequestDTO serieDTO){

    Serie serie = new Serie();
    serie.setId(serieDTO.getId());
    serie.setTitulo(serieDTO.getTitulo());
    serie.setDescricao(serieDTO.getDescricao());
    serie.setTemporadas(serieDTO.getTemporadas());
    serie.setEpisodios(serieDTO.getEpisodios());
    serie.setDataLancamento(serieDTO.getDataLancamento);
    serie.setNotaMedia(serieDTO.getNotaMedia());
    List<Categoria> categorias = new Categoria();
    serie.setCategorias(serieDTO.getCategorias());

    serieRepository.save(serie);

    return new SerieResponseDTO(serie);
}

@Transactional
public SerieResponseDTO atualizar(@Valid SerieRequestDTO serieDTO, Long id){
    Serie serie = serieRepository.findById(id)
         .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos uma Série com esse identificador."));

    serie.setId(serieDTO.getId());
    serie.setTitulo(serieDTO.getTitulo());
    serie.setDescricao(serieDTO.getDescricao());
    serie.setTemporadas(serieDTO.getTemporadas());
    serie.setEpisodios(serieDTO.getEpisodios());
    serie.setDataLancamento(serieDTO.getDataLancamento());
    serie.setNotaMedia(serieDTO.getNotaMedia());
    List<Categoria> categorias = new Categoria();
    serie.setCategorias(serieDTO.getCategorias());

    return new SerieResponseDTO(serie);

}

@Transactional
public void deletar(Long id){
    Serie serie = serieRepository.findById(id)
        .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos uma Série com esse identificador."));

        serieRepository.deleteById(id);
}


