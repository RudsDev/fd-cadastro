package com.firstdecision.cadatro.api.api.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.firstdecision.cadatro.api.domain.exceptions.ValidacaoException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	@Nullable
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
		MethodArgumentNotValidException ex, 
		HttpHeaders headers, 
		HttpStatusCode status, 
		WebRequest request
	) {

		return handleValidationInternal(
			ex, 
			headers,
			status,
			request, 
			ex.getBindingResult()
		);
	}

	@ExceptionHandler(ValidacaoException.class)
	public ResponseEntity<?> handleNegocioExpection(ValidacaoException ex, WebRequest request) {

		List<ExceptionObject.Campo> campos = new ArrayList<>();
		HttpStatus status = HttpStatus.BAD_REQUEST;

		campos.add(new ExceptionObject.Campo(ex.getNomeAtributo(), ex.getMessage()));
		
		ExceptionObject erro = createExceptionObjectBuilder("dados_invalidos", status, campos);
		return super.handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleRuntimeExpection(RuntimeException ex, WebRequest request) {
		List<ExceptionObject.Campo> campos = new ArrayList<>();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		campos.add(new ExceptionObject.Campo("Erro", "Erro interno do servidor."));
		ExceptionObject erro = createExceptionObjectBuilder("erro_interno", status, campos);
		return super.handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
	}

	private ResponseEntity<Object> handleValidationInternal(
		Exception ex,
		HttpHeaders headers,
		HttpStatusCode status,
		WebRequest request,
		BindingResult bindingResult
	) {
		List<ExceptionObject.Campo> campos = bindingResult
			.getAllErrors()
			.stream()
			.map(this::createCampo)
			.toList();

		ExceptionObject erro = createExceptionObjectBuilder("dados_invalidos", status, campos);
		return super.handleExceptionInternal(ex, erro, headers, status, request);
	}

	private ExceptionObject createExceptionObjectBuilder(
		String mensagem, 
		HttpStatusCode status, 
		List<ExceptionObject.Campo> campos
	) {
		return new ExceptionObject(status.value(), mensagem, Instant.now(), campos);
	}

	private ExceptionObject.Campo createCampo(ObjectError error) {
		return new ExceptionObject.Campo(
			((FieldError) error).getField(),
			error.getDefaultMessage()
		);
	}
}
