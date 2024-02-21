package com.firstdecision.cadatro.api.api.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.firstdecision.cadatro.api.domain.dtos.output.UsuarioOutput;
import com.firstdecision.cadatro.api.domain.models.Usuario;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

    @Autowired
    ObjectMapper mapper;
    
    @PostMapping()
    public ResponseEntity<UsuarioOutput> create(
       @Valid @RequestBody Usuario input
    ) {
        var output = mapper.convertValue(input, UsuarioOutput.class);
        return new ResponseEntity<UsuarioOutput>(output, HttpStatus.CREATED);
    }

}
