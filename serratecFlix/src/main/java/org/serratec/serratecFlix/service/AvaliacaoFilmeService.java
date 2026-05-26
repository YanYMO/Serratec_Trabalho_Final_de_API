package org.serratec.serratecFlix.service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.serratecFlix.dto.requestdto.AvaliacaoFilmeAtualizacaoDTO;
import org.serratec.serratecFlix.dto.requestdto.AvalicaoFilmeRequestDTO;
import org.serratec.serratecFlix.dto.responsedto.AvaliacaoFilmeResponseDTO;
import org.serratec.serratecFlix.entity.AvaliacaoFilme;
import org.serratec.serratecFlix.entity.Filme;
import org.serratec.serratecFlix.entity.Usuario;
import org.serratec.serratecFlix.exception.ValorDuplicadoException;
import org.serratec.serratecFlix.exception.ValorNaoEncontradoException;
import org.serratec.serratecFlix.repository.AvaliacaoFilmeRepository;
import org.serratec.serratecFlix.repository.FilmeRepository;
import org.serratec.serratecFlix.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoFilmeService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AvaliacaoFilmeRepository avaliacaoFilmeRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    public List<AvaliacaoFilmeResponseDTO> findAll() {
        List<AvaliacaoFilme> avaliacoes = avaliacaoFilmeRepository.findAll();

        if (avaliacoes.isEmpty()) {
            throw new ValorDuplicadoException("Não existem Avaliações cadastrados.");
        }
        List<AvaliacaoFilmeResponseDTO> avaliacaoFilmeDTO = new ArrayList<>();

        for (AvaliacaoFilme avaliacaoFilme : avaliacoes) {
            avaliacaoFilmeDTO.add(new AvaliacaoFilmeResponseDTO(avaliacaoFilme));
        }
        return avaliacaoFilmeDTO;
    }

    public AvaliacaoFilmeResponseDTO findById(Long id) {
        AvaliacaoFilme avaliacoes = avaliacaoFilmeRepository.findById(id)
                .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos uma Avaliação de Filme com esse identificador."));
        AvaliacaoFilmeResponseDTO avaliacaoFilmeDTO = new AvaliacaoFilmeResponseDTO(avaliacoes);
        return avaliacaoFilmeDTO;
    }

    public AvaliacaoFilmeResponseDTO cadastrar(AvalicaoFilmeRequestDTO avaliacaoFilmeRequest) {
        Usuario usuario = usuarioRepository.findById(avaliacaoFilmeRequest.getUsuarioId())
                .orElseThrow(() -> new ValorNaoEncontradoException("Usuário não encontrado."));

        Filme filme = filmeRepository.findById(avaliacaoFilmeRequest.getFilmeId())
                .orElseThrow(() -> new ValorNaoEncontradoException("Filme não encontrado."));

        AvaliacaoFilme avaliacaoFilme = new AvaliacaoFilme();
        avaliacaoFilme.setNota(avaliacaoFilmeRequest.getNota());
        avaliacaoFilme.setComentario(avaliacaoFilmeRequest.getComentario());
        avaliacaoFilme.setUsuario(usuario);
        avaliacaoFilme.setFilme(filme);

        AvaliacaoFilmeResponseDTO response = new AvaliacaoFilmeResponseDTO(avaliacaoFilmeRepository.save(avaliacaoFilme));
        atualizarMediaFilme(filme);
        return response;
    }

    public AvaliacaoFilmeResponseDTO atualizar(Long id, AvaliacaoFilmeAtualizacaoDTO avaliacaoFilmeAtualizacao) {
        AvaliacaoFilme avaliacaoFilme = avaliacaoFilmeRepository.findById(id)
                .orElseThrow(() -> new ValorNaoEncontradoException("Avaliação não encontrada."));
        avaliacaoFilme.setNota(avaliacaoFilmeAtualizacao.getNota());
        avaliacaoFilme.setComentario(avaliacaoFilmeAtualizacao.getComentario());

        AvaliacaoFilmeResponseDTO response = new AvaliacaoFilmeResponseDTO(avaliacaoFilmeRepository.save(avaliacaoFilme));
        atualizarMediaFilme(avaliacaoFilme.getFilme());
        return response;
    }

    public void deletar(Long id) {
        AvaliacaoFilme avaliacaoFilme = avaliacaoFilmeRepository.findById(id)
                .orElseThrow(() -> new ValorNaoEncontradoException("Avaliação não encontrada."));

        Filme filme = avaliacaoFilme.getFilme();
        avaliacaoFilmeRepository.deleteById(id);
        atualizarMediaFilme(filme);
    }

    private void atualizarMediaFilme(Filme filme) {
        List<AvaliacaoFilme> avaliacoes = avaliacaoFilmeRepository.findByFilme(filme);

        if (avaliacoes.isEmpty()) {
            filme.setNotaMedia(0.0);
        } else {
            int soma = 0;
            for (AvaliacaoFilme avaliacao : avaliacoes) {
                soma += avaliacao.getNota();
            }
            double media = (double) soma / avaliacoes.size();
            filme.setNotaMedia(media);
        }
        filmeRepository.save(filme);
    }
}