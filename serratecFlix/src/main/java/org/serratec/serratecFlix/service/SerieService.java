package org.serratec.serratecFlix.service;

import jakarta.transaction.Transactional;
import org.serratec.serratecFlix.dto.requestdto.SerieRequestDTO;
import org.serratec.serratecFlix.dto.responsedto.SerieResponseDTO;
import org.serratec.serratecFlix.entity.Categoria;
import org.serratec.serratecFlix.entity.ListaFavoritos;
import org.serratec.serratecFlix.entity.Serie;
import org.serratec.serratecFlix.entity.Usuario;
import org.serratec.serratecFlix.enums.ClassificacaoIndicativa;
import org.serratec.serratecFlix.exception.IdadeInsuficienteException;
import org.serratec.serratecFlix.exception.ValorNaoEncontradoException;
import org.serratec.serratecFlix.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ExperienciaService experienciaService;
    @Autowired
    private ListaFavoritosRepository listaFavoritosRepository;
    @Autowired
    private PremioRepository premioRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

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

    public SerieResponseDTO findById(Long id, String username) {
        Serie serie = serieRepository.findById(id)
            .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos uma Série com esse identificador."));

        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new ValorNaoEncontradoException("Usuario não encontrado");
        }

        verificarIdade(usuario, serie.getTitulo(), serie.getClassificacao());

        SerieResponseDTO serieDTO = new SerieResponseDTO(serie);
        
        //Experiencia
        
        experienciaService.atualizar(usuario, 7);

        return serieDTO;
    }

    @Transactional
    public SerieResponseDTO cadastrar(SerieRequestDTO serieDTO) {
        List<Categoria> categorias = categoriaRepository.findAllById(serieDTO.getCategorias());
        if (categorias.isEmpty()){
            throw new ValorNaoEncontradoException("Alguma categoria informada, não está disponível.");
        }
        
        Serie serie = new Serie();
        serie.setTitulo(serieDTO.getTitulo());
        serie.setDescricao(serieDTO.getDescricao());
        serie.setTemporadas(serieDTO.getTemporadas());
        serie.setEpisodios(serieDTO.getEpisodios());
        serie.setDataLancamento(serieDTO.getDataLancamento());
        serie.setClassificacao(serieDTO.getClassificacao());
        serie.setNotaMedia(0.0);
        serie.setCategorias(categorias);
        
        serieRepository.save(serie);
    
        return new SerieResponseDTO(serie);
    }

    @Transactional
    public SerieResponseDTO atualizar(Long id, SerieRequestDTO serieDTO){
        Serie serie = serieRepository.findById(id)
             .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos uma Série com esse identificador."));

        List<Categoria> categorias = categoriaRepository.findAllById(serieDTO.getCategorias());
        if (categorias.isEmpty()){
            throw new ValorNaoEncontradoException("Alguma categoria informada, não está disponível.");
        }
        
        serie.setTitulo(serieDTO.getTitulo());
        serie.setDescricao(serieDTO.getDescricao());
        serie.setTemporadas(serieDTO.getTemporadas());
        serie.setEpisodios(serieDTO.getEpisodios());
        serie.setDataLancamento(serieDTO.getDataLancamento());
        serie.setClassificacao(serieDTO.getClassificacao());
        serie.setCategorias(categorias);
    
        serieRepository.save(serie);
        
        return new SerieResponseDTO(serie);
    }

    @Transactional
    public void deletar(Long id){
         Serie serie = serieRepository.findById(id)
            .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos uma Série com esse identificador."));

        serie.getCategorias().clear();
        serieRepository.save(serie);

        List<ListaFavoritos> listas = listaFavoritosRepository.findBySeriesId(id);
        for (ListaFavoritos lista : listas) {
            lista.getSeries().remove(serie);
            listaFavoritosRepository.save(lista);
        }

        serieRepository.deleteById(id);
    }

    private void verificarIdade(Usuario usuario, String titulo, ClassificacaoIndicativa classificacao) {

        Integer idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();

        if (idade < classificacao.getIdadeMinima()) {
            throw new IdadeInsuficienteException(titulo, classificacao.getIdadeMinima());
        }
    }
}


