package org.serratec.serratecFlix.service;

import jakarta.transaction.Transactional;
import org.serratec.serratecFlix.dto.requestdto.FilmeRequestDTO;
import org.serratec.serratecFlix.dto.responsedto.FilmeResponseDTO;
import org.serratec.serratecFlix.dto.responsedto.OmdbFilmeResponseDTO;
import org.serratec.serratecFlix.entity.*;
import org.serratec.serratecFlix.enums.ClassificacaoIndicativa;
import org.serratec.serratecFlix.exception.IdadeInsuficienteException;
import org.serratec.serratecFlix.exception.ValorNaoEncontradoException;
import org.serratec.serratecFlix.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ExperienciaService experienciaService;
    @Autowired
    private ListaFavoritosRepository listaFavoritosRepository;
    @Autowired
    private HistoricoAssistidoRepository historicoAssistidoRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<FilmeResponseDTO> findAll() {
        List<Filme> filmes = filmeRepository.findAll();

        if (filmes.isEmpty()) {
            throw new ValorNaoEncontradoException("Não existem Filmes cadastrados.");
        }
        List<FilmeResponseDTO> filmeDTO = new ArrayList<FilmeResponseDTO>();

        for (Filme filme : filmes) {
            filmeDTO.add(new FilmeResponseDTO(filme));
        }
        return filmeDTO;
    }

    public FilmeResponseDTO findById(Long id, String username) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos um Filme com esse identificador."));

        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new ValorNaoEncontradoException("Usuario não encontrado");
        }

        verificarIdade(usuario, filme.getTitulo(), filme.getClassificacao());

        FilmeResponseDTO filmeDTO = new FilmeResponseDTO(filme);
        
        //Experiencia:
        
        experienciaService.atualizar(usuario, 4);

        return filmeDTO;
    }

    @Transactional
    public FilmeResponseDTO cadastrar(FilmeRequestDTO filmeDTO) {
        List<Categoria> categorias = categoriaRepository.findAllById(filmeDTO.getCategorias());
        if (categorias.isEmpty()) {
            throw new ValorNaoEncontradoException("Alguma categoria informada, não está disponível.");
        }

        Filme filme = new Filme();
        filme.setTitulo(filmeDTO.getTitulo());
        filme.setDescricao(filmeDTO.getDescricao());
        filme.setDuracaoMinutos(filmeDTO.getDuracaoMinutos());
        filme.setDataLancamento(filmeDTO.getDataLancamento());
        filme.setClassificacao(filmeDTO.getClassificacao());
        filme.setNotaMedia(0.0);
        filme.setCategorias(categorias);

        filmeRepository.save(filme);

        return new FilmeResponseDTO(filme);
    }

    @Transactional
    public FilmeResponseDTO atualizar(Long id, FilmeRequestDTO filmeDTO) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos um Filme com esse identificador."));

        List<Categoria> categoria = categoriaRepository.findAllById(filmeDTO.getCategorias());
        if (categoria.isEmpty()) {
            throw new ValorNaoEncontradoException("Alguma categoria informada, não está disponível.");
        }

        filme.setTitulo(filmeDTO.getTitulo());
        filme.setDescricao(filmeDTO.getDescricao());
        filme.setDuracaoMinutos(filmeDTO.getDuracaoMinutos());
        filme.setDataLancamento(filmeDTO.getDataLancamento());
        filme.setClassificacao(filmeDTO.getClassificacao());
        filme.setCategorias(categoria);

        filmeRepository.save(filme);

        return new FilmeResponseDTO(filme);
    }

    @Transactional
    public void deletar(Long id) {
         Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos um Filme com esse identificador."));

         filme.getCategorias().clear();
         filmeRepository.save(filme);

         List<ListaFavoritos> listas = listaFavoritosRepository.findByFilmesId(id);
         for (ListaFavoritos lista : listas) {
             lista.getFilmes().remove(filme);
             listaFavoritosRepository.save(lista);
         }

        List<HistoricoAssistido> assistidos = historicoAssistidoRepository.findByFilmeId(id);
        for (HistoricoAssistido assistido : assistidos) {
            assistido.getFilme().getHistoricos().remove(filme);
        }

        filmeRepository.deleteById(id);
    }

    private void verificarIdade(Usuario usuario, String titulo, ClassificacaoIndicativa classificacao) {

        Integer idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();

        if (idade < classificacao.getIdadeMinima()) {
            throw new IdadeInsuficienteException(titulo, classificacao.getIdadeMinima());
        }
    }
    
    public OmdbFilmeResponseDTO buscarApiExterna(String titulo) {
    RestTemplate restTemplate = new RestTemplate();
    String url = "https://www.omdbapi.com/?t=" + titulo + "&apikey=1ee52030";
    return restTemplate.getForObject(url, OmdbFilmeResponseDTO.class); 
}
}
