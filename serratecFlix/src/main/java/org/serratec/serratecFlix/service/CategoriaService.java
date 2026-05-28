package org.serratec.serratecFlix.service;

import jakarta.transaction.Transactional;
import org.serratec.serratecFlix.dto.requestdto.CategoriaRequestDTO;
import org.serratec.serratecFlix.dto.responsedto.CategoriaResponseDTO;
import org.serratec.serratecFlix.entity.Categoria;
import org.serratec.serratecFlix.entity.Filme;
import org.serratec.serratecFlix.entity.Serie;
import org.serratec.serratecFlix.exception.ValorNaoEncontradoException;
import org.serratec.serratecFlix.repository.CategoriaRepository;
import org.serratec.serratecFlix.repository.FilmeRepository;
import org.serratec.serratecFlix.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CategoriaService {
   
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private FilmeRepository filmeRepository;
    @Autowired
    private SerieRepository serieRepository;

    public List<CategoriaResponseDTO> findAll(){
        List<Categoria> categorias = categoriaRepository.findAll();

        if(categorias.isEmpty()){
            throw new ValorNaoEncontradoException("Não existem Categorias cadastradas.");

        }

        List<CategoriaResponseDTO> categoriaDTO = new ArrayList<>();
        for(Categoria categoria : categorias){
            categoriaDTO.add(new CategoriaResponseDTO(categoria));
        }
        return categoriaDTO;
    }

    public CategoriaResponseDTO findById(long id){
        Categoria categoria = categoriaRepository.findById(id)
              .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos uma Categoria com esse identificador."));

              return new CategoriaResponseDTO(categoria);
    }

    @Transactional
    public CategoriaResponseDTO cadastrar( CategoriaRequestDTO categoriaDTO){
          Categoria categoria = new Categoria();
          categoria.setNome(categoriaDTO.getNome());
          categoria.setDescricao(categoriaDTO.getDescricao());
          

          categoriaRepository.save(categoria);
          return new CategoriaResponseDTO(categoria);
    }

    @Transactional
    public CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO categoriaDTO){
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos uma Categoria com esse identificador."));
        
        categoria.setNome(categoriaDTO.getNome());
        categoria.setDescricao(categoriaDTO.getDescricao());

        return new CategoriaResponseDTO(categoria);

    }

    @Transactional
    public void deletar(Long id){
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos uma Categoria com esse identificador."));

        List<Filme> filmes = filmeRepository.findByCategoriasId(id);
        for (Filme filme : filmes) {
            filme.getCategorias().remove(categoria);
            filmeRepository.save(filme);
        }

        List<Serie> series = serieRepository.findByCategoriasId(id);
        for (Serie serie : series) {
            serie.getCategorias().remove(categoria);
            serieRepository.save(serie);
        }

        categoriaRepository.deleteById(id);
    }
}
