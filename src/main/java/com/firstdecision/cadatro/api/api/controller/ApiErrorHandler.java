package com.firstdecision.cadatro.api.api.controller;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiErrorHandler {

    public static ResponseEntity<?> badRequest(String mensagem) {
        return error(mensagem, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<?> notFound(String mensagem) {
        return error(mensagem, HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<?> internalServerError(String mensagem) {
        return error(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<?> unauthorized(String mensagem) {
        return error(mensagem, HttpStatus.UNAUTHORIZED);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static ResponseEntity<?> error(String mensagem, HttpStatus httpStatus) {
        Map<Object, Object> model = new HashMap<>();
        model.put("phrase", httpStatus.getReasonPhrase());
        model.put("status", httpStatus.value());
        model.put("message", mensagem);
        model.put("timestamp", Instant.now().toString());

        return new ResponseEntity(model, httpStatus);
    }

}
