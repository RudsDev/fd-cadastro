package com.firstdecision.cadatro.api.api.exceptions;

import java.time.Instant;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
