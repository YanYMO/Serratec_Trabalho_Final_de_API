package org.serratec.serratecFlix.service;

import jakarta.transaction.Transactional;

import org.serratec.serratecFlix.DTO.RequestDTO.FilmeRequestDTO;
import org.serratec.serratecFlix.DTO.ResponseDTO.FilmeResponseDTO;
import org.serratec.serratecFlix.entity.Filme;
import org.serratec.serratecFlix.entity.Usuario;
import org.serratec.serratecFlix.enums.ClassificacaoIndicativa;
import org.serratec.serratecFlix.exception.IdadeInsuficienteException;
import org.serratec.serratecFlix.exception.ValorNaoEncontradoException;
import org.serratec.serratecFlix.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

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

    public FilmeResponseDTO findById(Long id) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos um Filme com esse identificador."));

        FilmeResponseDTO filmeDTO = new FilmeResponseDTO(filme);

        return filmeDTO;
    }

    @Transactional
    public FilmeResponseDTO cadastrar( FilmeRequestDTO filmeDTO) {

        Filme filme = new Filme();
        filme.setTitulo(filmeDTO.getTitulo());
        filme.setDescricao(filmeDTO.getDescricao());
        filme.setDuracaoMinutos(filmeDTO.getDuracaoMinutos());
        filme.setDataLancamento(filmeDTO.getDataLancamento());
        filme.setClassificacao(filmeDTO.getClassificacao());
        filme.setCategorias(filmeDTO.getCategorias());

        filmeRepository.save(filme);

        return new FilmeResponseDTO(filme);
    }

    @Transactional
    public FilmeResponseDTO atualizar(Long id, FilmeRequestDTO filmeDTO) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos um Filme com esse identificador."));

        filme.setTitulo(filmeDTO.getTitulo());
        filme.setDescricao(filmeDTO.getDescricao());
        filme.setDuracaoMinutos(filmeDTO.getDuracaoMinutos());
        filme.setCategorias(filmeDTO.getCategorias());

        filmeRepository.save(filme);

        return new FilmeResponseDTO(filme);
    }

    @Transactional
    public void deletar(Long id) {
         filmeRepository.findById(id)
                .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos um Filme com esse identificador."));

        filmeRepository.deleteById(id);
    }

    private void verificarIdade(Usuario usuario, String titulo, ClassificacaoIndicativa classificacao) {

        Integer idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();

        if (idade < classificacao.getIdadeMinima()) {
            throw new IdadeInsuficienteException(titulo, classificacao.getIdadeMinima());
        }
    }
}
