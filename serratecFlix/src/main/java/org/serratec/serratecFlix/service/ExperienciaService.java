package org.serratec.serratecFlix.service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.serratecFlix.dto.responsedto.ExperienciaResponseDTO;
import org.serratec.serratecFlix.entity.Experiencia;
import org.serratec.serratecFlix.entity.Usuario;
import org.serratec.serratecFlix.exception.ValorNaoEncontradoException;
import org.serratec.serratecFlix.repository.ExperienciaRepository;
import org.serratec.serratecFlix.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ExperienciaService {
	
	@Autowired
	private ExperienciaRepository experienciaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public ExperienciaResponseDTO findById(Long id) {
		
		Usuario usu = usuarioRepository.findById(id)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não foi encontrado nenhum Usuario com esse id"));
		
		ExperienciaResponseDTO expResponse = new ExperienciaResponseDTO(usu);
		return expResponse;
	}
	
	@Transactional
	public ExperienciaResponseDTO findByUsername(String userName) {
		
		Usuario usu = usuarioRepository.findByUsername(userName);
			
		if (usu == null) {
            throw new ValorNaoEncontradoException("O usuario não possui uma conta ou um Username!");
        }
		
		ExperienciaResponseDTO expResponse = new ExperienciaResponseDTO(usu);
		return expResponse;
	}
	
	@Transactional
	public List<ExperienciaResponseDTO> findAll() {
		
		List<Experiencia> exp = experienciaRepository.findAll();
		List<ExperienciaResponseDTO> expDTO = new ArrayList<>();
		
		if (exp.isEmpty()) {
			throw new ValorNaoEncontradoException("Não existe nenhum usuario com nivel cadastrado");
		}
		
        for (Experiencia xp : exp) {
            expDTO.add(new ExperienciaResponseDTO(xp));
        }
        
        return expDTO;
	}
	
	/*
	 * 
	 * 				MENÇÃO HONROSA:
	 * 
	 * Jaz aqui, a função nunca utilizada.
	
	@Transactional
	public Experiencia cadastrar(Usuario usuario) {
		
		Experiencia exp = new Experiencia();
		exp.setXp(0);
		exp.setNivel(1);
		exp.setUsuario(usuario);
		
		experienciaRepository.save(exp);
		
		return exp;
	}
	*/
	
	@Transactional
	public ExperienciaResponseDTO atualizar(Usuario usuario, Integer experiencia) {
		
		Experiencia exp = usuario.getExperiencia();
		
		exp.setXp(exp.getXp()+experiencia);
		exp.setNivel((exp.getXp()/80)+1);
		
		experienciaRepository.save(exp);
		
		ExperienciaResponseDTO expResponse = new ExperienciaResponseDTO(exp);	
		return expResponse;
	}
}
