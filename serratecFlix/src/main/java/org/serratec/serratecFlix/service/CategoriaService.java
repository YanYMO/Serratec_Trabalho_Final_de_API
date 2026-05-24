package org.serratec.serratecFlix.service;

import jakarta.transaction.Transactional;
import org.serratec.serratecFlix.RequestDTO.CategoriaRequestDTO;
import org.serratec.serratecFlix.ResponseDTO.CategoriaResponseDTO;
import org.serratec.serratecFlix.entity.Categoria;
import org.serratec.serratecFlix.exception.ValorNaoEncontradoException;
import org.serratec.serratecFlix.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {
   
    @Autowired
    private CategoriaRepository categoriaRepository;

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
    public CategoriaResponseDTO atualizar( CategoriaRequestDTO categoriaDTO, Long id){
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos uma Categoria com esse identificador."));
        
        categoria.setNome(categoriaDTO.getNome());
        categoria.setDescricao(categoriaDTO.getDescricao());

        return new CategoriaResponseDTO(categoria);

    }

    @Transactional
    public void deletar(Long id){
        categoriaRepository.findById(id)
                .orElseThrow(() -> new ValorNaoEncontradoException("Não encontramos uma Categoria com esse identificador."));

        categoriaRepository.deleteById(id);
    }


}
