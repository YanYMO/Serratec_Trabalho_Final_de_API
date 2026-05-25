package org.serratec.serratecFlix.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Override
	public ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		ErroResposta erro = new ErroResposta(HttpStatus.BAD_REQUEST.value(), "A variável utilizada no caminho está incorreta ou está faltando!");
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		FieldError error = ex.getFieldError();
		String erroMsg = error.getField() + ":" + error.getDefaultMessage();
		
		ErroResposta erro = new ErroResposta(status.value(), erroMsg);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErroResposta> handleTypeMismatch(org.springframework.web.method.annotation.MethodArgumentTypeMismatchException ex) {
		
		ErroResposta erro = new ErroResposta(HttpStatus.BAD_REQUEST.value(), "Valor de Entrada Inválido! Verifique e tente novamente");
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(ValorNaoEncontradoException.class)
	public ResponseEntity<ErroResposta> ValorNaoEncontrado (ValorNaoEncontradoException ex){
		
		ErroResposta erro = new ErroResposta(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}	
	
	@ExceptionHandler(ValorDuplicadoException.class)
	public ResponseEntity<ErroResposta> ValorDuplicado (ValorDuplicadoException ex){

		ErroResposta erro = new ErroResposta(HttpStatus.CONFLICT.value(), ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErroResposta> handleDataIntegrity(DataIntegrityViolationException ex) {
	    ErroResposta erro = new ErroResposta(
	        HttpStatus.BAD_REQUEST.value(), 
	        "Erro de integridade: verifique se todos os campos obrigatórios foram preenchidos corretamente."
	    );
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

	@ExceptionHandler(InvalidDataAccessApiUsageException.class)
	public ResponseEntity<ErroResposta> handleInvalidId(InvalidDataAccessApiUsageException ex) {
	    ErroResposta erro = new ErroResposta(
	        HttpStatus.BAD_REQUEST.value(), 
	        "ID inválido ou nulo. Verifique os campos de relacionamento (idProfessor, idCurso, idAlunos)."
	    );
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErroResposta> handleConstraint(ConstraintViolationException ex) {
	    ErroResposta erro = new ErroResposta(
	        HttpStatus.BAD_REQUEST.value(), 
	        "Erro de validação: " + ex.getMessage()
	    );
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(EnumValidationException.class)
	public ResponseEntity<ErroResposta> ValorNaoEncontrado (EnumValidationException ex){
		
		ErroResposta erro = new ErroResposta(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

	@ExceptionHandler(IdadeInsuficienteException.class)
	public ResponseEntity<ErroResposta> handleIdade (IdadeInsuficienteException ex) {
		ErroResposta erro = new ErroResposta (
				HttpStatus.FORBIDDEN.value(),
				ex.getMessage());

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro);
	}
}