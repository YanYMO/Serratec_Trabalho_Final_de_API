package org.serratec.serratecFlix.service;

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
		
		Experiencia exp = usu.getExperiencia();
		
		ExperienciaResponseDTO expResponse = new ExperienciaResponseDTO(exp.getXp(), exp.getNivel());
		return expResponse;
	}
	
	@Transactional
	public ExperienciaResponseDTO cadastrar(Usuario usuario) {
		
		Experiencia exp = new Experiencia();
		exp.setXp(0);
		exp.setNivel(1);
		exp.setUsuario(usuario);
		
		experienciaRepository.save(exp);
		
		ExperienciaResponseDTO expResponse = new ExperienciaResponseDTO(exp.getXp(), exp.getNivel());
		
		return expResponse;
	}
	
	@Transactional
	public ExperienciaResponseDTO atualizar(Usuario usuario, Integer experiencia) {
		
		Experiencia exp = usuario.getExperiencia();
		
		exp.setXp(exp.getXp()+experiencia);
		exp.setNivel((exp.getXp()/80)+1);
		
		experienciaRepository.save(exp);
		
		ExperienciaResponseDTO expResponse = new ExperienciaResponseDTO(exp.getXp(), exp.getNivel());	
		return expResponse;
	}
}
