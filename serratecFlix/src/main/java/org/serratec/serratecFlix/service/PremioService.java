package org.serratec.serratecFlix.service;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.serratec.serratecFlix.dto.requestdto.PremioRequestDTO;
import org.serratec.serratecFlix.dto.responsedto.PremioResponseDTO;
import org.serratec.serratecFlix.entity.Filme;
import org.serratec.serratecFlix.entity.Premio;
import org.serratec.serratecFlix.entity.Serie;
import org.serratec.serratecFlix.exception.ValorNaoEncontradoException;
import org.serratec.serratecFlix.repository.FilmeRepository;
import org.serratec.serratecFlix.repository.PremioRepository;
import org.serratec.serratecFlix.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PremioService {

    @Autowired
    private PremioRepository premioRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PremioResponseDTO inserir(PremioRequestDTO premioDTO) {
        if (premioDTO.getFilmeId() == null && premioDTO.getSerieId() == null) {
            throw new ValorNaoEncontradoException("Informe um filme ou série válidos");
        }

        Premio premio = new Premio();
        premio.setNome(premioDTO.getNome());
        premio.setCategoria(premioDTO.getCategoria());
        premio.setAno(premioDTO.getAno());
        

        if (premioDTO.getFilmeId() != null) {
            Filme filme = filmeRepository.findById(premioDTO.getFilmeId())
                    .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos nenhum filme com esse identificador"));
            premio.setFilme(filme);
        }

        if (premioDTO.getSerieId() != null) {
            Serie serie = serieRepository.findById(premioDTO.getSerieId())
                    .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos nenhuma série com esse identificador"));
            premio.setSerie(serie);
        }

        return toResponseDTO(premioRepository.save(premio));
    }

    public List<PremioResponseDTO> buscarTodos() {
         List<Premio> premios = premioRepository.findAll();

    if (premios.isEmpty()) {
        throw new ValorNaoEncontradoException("Nenhum prêmio encontrado.");
    }

        return premioRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public PremioResponseDTO buscarPorId(Long id) {
        Premio premio = premioRepository.findById(id)
                .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos nenhum Prêmio com esse identificador"));
        return toResponseDTO(premio);
    }

    public List<PremioResponseDTO> buscarPorFilme(Long idFilme) {
         List<Premio> premios = premioRepository.findByFilmeId(idFilme);

    if (premios.isEmpty()) {
          throw new ValorNaoEncontradoException("Nenhum prêmio encontrado para o filme.");
      }
        return premioRepository.findByFilmeId(idFilme)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public List<PremioResponseDTO> buscarPorSerie(Long idSerie) {
        List<Premio> premios = premioRepository.findBySerieId(idSerie);

    if (premios.isEmpty()) {
        throw new ValorNaoEncontradoException("Nenhum prêmio encontrado para a série.");
    }
        
        return premioRepository.findBySerieId(idSerie)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }


    public List<PremioResponseDTO> buscarPorAno(Integer ano) {
        List<Premio> premios = premioRepository.buscarPorAno(ano);

    if (premios.isEmpty()) {
        throw new ValorNaoEncontradoException("Nenhum prêmio encontrado para o ano informado.");
    }

        return premioRepository.buscarPorAno(ano)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public PremioResponseDTO atualizar(Long id, PremioRequestDTO premioDTO) {
        Premio premio = premioRepository.findById(id)
                .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos nenhum Prêmio com esse identificador"));

        premio.setNome(premioDTO.getNome());
        premio.setCategoria(premioDTO.getCategoria());
        premio.setAno(premioDTO.getAno());
        

        return toResponseDTO(premioRepository.save(premio));
    }

    public void deletar(Long id) {
        if (!premioRepository.existsById(id)) {
            throw new ValorNaoEncontradoException("Não encontramos nenhum Prêmio com esse identificador");
        }
        premioRepository.deleteById(id);
    }

    private PremioResponseDTO toResponseDTO(Premio premio) {
        PremioResponseDTO premioDTO = modelMapper.map(premio, PremioResponseDTO.class);

        if (premio.getFilme() != null) {
            premioDTO.setNomeConteudo(premio.getFilme().getTitulo());
            premioDTO.setTipoConteudo("FILME");
        } else {
            premioDTO.setNomeConteudo(premio.getSerie().getTitulo());
            premioDTO.setTipoConteudo("SERIE");
        }

        return premioDTO;
    }
}
