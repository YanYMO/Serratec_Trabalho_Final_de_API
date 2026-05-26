package org.serratec.serratecFlix.service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.serratecFlix.dto.requestdto.ListaFavoritosRequestDTO;
import org.serratec.serratecFlix.dto.responsedto.ListaFavoritosResponseDTO;
import org.serratec.serratecFlix.entity.Filme;
import org.serratec.serratecFlix.entity.ListaFavoritos;
import org.serratec.serratecFlix.entity.Serie;
import org.serratec.serratecFlix.entity.Usuario;
import org.serratec.serratecFlix.exception.ValorNaoEncontradoException;
import org.serratec.serratecFlix.repository.FilmeRepository;
import org.serratec.serratecFlix.repository.ListaFavoritosRepository;
import org.serratec.serratecFlix.repository.SerieRepository;
import org.serratec.serratecFlix.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public ListaFavoritosResponseDTO cadastrar(ListaFavoritosRequestDTO listaFavoritosRequest) {
        Usuario usuario = usuarioRepository.findById(listaFavoritosRequest.getUsuarioId())
                .orElseThrow(() -> new ValorNaoEncontradoException("Usuário não encontrado."));

        List<Filme> filmes = filmeRepository.findAllById(listaFavoritosRequest.getFilmesIds());
        List<Serie> series = serieRepository.findAllById(listaFavoritosRequest.getSeriesIds());

        ListaFavoritos lista = new ListaFavoritos();
        lista.setNomeLista(listaFavoritosRequest.getNomeLista());
        lista.setPrivada(listaFavoritosRequest.getPrivada());
        lista.setUsuario(usuario);
        lista.setFilmes(filmes);
        lista.setSeries(series);

        listaFavoritosRepository.save(lista);

        return new ListaFavoritosResponseDTO(lista);
    }

    public ListaFavoritosResponseDTO atualizar(Long id, ListaFavoritosRequestDTO listaFavoritosRequest) {
        ListaFavoritos lista = listaFavoritosRepository.findById(id)
                .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos uma Lista de Favoritos com esse identificador."));

        List<Filme> filmes = filmeRepository.findAllById(listaFavoritosRequest.getFilmesIds());
        List<Serie> series = serieRepository.findAllById(listaFavoritosRequest.getSeriesIds());

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
}