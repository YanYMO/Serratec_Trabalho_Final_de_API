package org.serratec.serratecFlix.service;

import org.serratec.serratecFlix.dto.requestdto.ListaFavoritosRequestDTO;
import org.serratec.serratecFlix.dto.responsedto.ListaFavoritosResponseDTO;
import org.serratec.serratecFlix.entity.Filme;
import org.serratec.serratecFlix.entity.ListaFavoritos;
import org.serratec.serratecFlix.entity.Serie;
import org.serratec.serratecFlix.entity.Usuario;
import org.serratec.serratecFlix.enums.ClassificacaoIndicativa;
import org.serratec.serratecFlix.exception.IdadeInsuficienteException;
import org.serratec.serratecFlix.exception.ValorNaoEncontradoException;
import org.serratec.serratecFlix.exception.ValorNecessarioException;
import org.serratec.serratecFlix.repository.FilmeRepository;
import org.serratec.serratecFlix.repository.ListaFavoritosRepository;
import org.serratec.serratecFlix.repository.SerieRepository;
import org.serratec.serratecFlix.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class ListaFavoritosService {

    @Autowired
    private ListaFavoritosRepository listaFavoritosRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private SerieRepository serieRepository;

    public List<ListaFavoritosResponseDTO> findAll() {
        List<ListaFavoritos> listas = listaFavoritosRepository.findAll();

        if (listas.isEmpty()) {
            throw new ValorNaoEncontradoException("Não existem Listas de Favoritos cadastradas.");
        }

        List<ListaFavoritosResponseDTO> listasDTO = new ArrayList<>();
        for (ListaFavoritos lista : listas) {
            listasDTO.add(new ListaFavoritosResponseDTO(lista));
        }
        return listasDTO;
    }

    public ListaFavoritosResponseDTO findById(Long id) {
        ListaFavoritos lista = listaFavoritosRepository.findById(id)
                .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos uma Lista de Favoritos com esse identificador."));

        ListaFavoritosResponseDTO listaDTO = new ListaFavoritosResponseDTO(lista);
        return listaDTO;
    }

    public ListaFavoritosResponseDTO cadastrar(ListaFavoritosRequestDTO listaFavoritosRequest, String username) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new ValorNaoEncontradoException("Usuario não encontrado");
        }

        List<Filme> filmes = filmeRepository.findAllById(listaFavoritosRequest.getIdFilmes());
        List<Serie> series = serieRepository.findAllById(listaFavoritosRequest.getIdSeries());
        
        if(filmes.isEmpty() && series.isEmpty()) {
            throw new ValorNecessarioException("Precisa ter pelo menos um filme e uma série para criar a Lista");
        }

        for (Filme filme : filmes) {
            verificarIdade(usuario, filme.getTitulo(), filme.getClassificacao());
        }
        for (Serie serie : series) {
            verificarIdade(usuario, serie.getTitulo(), serie.getClassificacao());
        }

        ListaFavoritos lista = new ListaFavoritos();
        lista.setNomeLista(listaFavoritosRequest.getNomeLista());
        lista.setPrivada(listaFavoritosRequest.getPrivada());
        lista.setUsuario(usuario);
        lista.setFilmes(filmes);
        lista.setSeries(series);

        listaFavoritosRepository.save(lista);

        return new ListaFavoritosResponseDTO(lista);
    }

    public ListaFavoritosResponseDTO atualizar(Long id, ListaFavoritosRequestDTO listaFavoritosRequest, String username) {
        ListaFavoritos lista = listaFavoritosRepository.findById(id)
                .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos uma Lista de Favoritos com esse identificador."));

        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new ValorNaoEncontradoException("Usuario não encontrado");
        }

        if (lista.getUsuario().getId() != usuario.getId()) {
            throw new ValorNaoEncontradoException("Você não tem permissão para atualizar esta lista de favoritos.");
        }
        
        List<Filme> filmes = filmeRepository.findAllById(listaFavoritosRequest.getIdFilmes());
        List<Serie> series = serieRepository.findAllById(listaFavoritosRequest.getIdSeries());
        
        if(filmes.isEmpty() && series.isEmpty()) {
            throw new ValorNecessarioException("Precisa ter pelo menos um filme e uma série para criar a Lista");
        }

        for (Filme filme : filmes) {
            verificarIdade(usuario, filme.getTitulo(), filme.getClassificacao());
        }
        for (Serie serie : series) {
            verificarIdade(usuario, serie.getTitulo(), serie.getClassificacao());
        }

        lista.setNomeLista(listaFavoritosRequest.getNomeLista());
        lista.setPrivada(listaFavoritosRequest.getPrivada());
        lista.setFilmes(filmes);
        lista.setSeries(series);

        listaFavoritosRepository.save(lista);

        return new ListaFavoritosResponseDTO(lista);
    }

    public void deletar(Long id) {
        listaFavoritosRepository.findById(id)
                .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos uma Lista de Favoritos com esse identificador."));

        listaFavoritosRepository.deleteById(id);
    }

    private void verificarIdade(Usuario usuario, String titulo, ClassificacaoIndicativa classificacao) {

        Integer idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();

        if (idade < classificacao.getIdadeMinima()) {
            throw new IdadeInsuficienteException(titulo, classificacao.getIdadeMinima());
        }
    }
}