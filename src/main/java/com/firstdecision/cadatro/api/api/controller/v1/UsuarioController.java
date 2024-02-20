package com.firstdecision.cadatro.api.api.controller.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firstdecision.cadatro.api.domain.models.Usuario;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {
    
    @PostMapping()
    public ResponseEntity<Usuario> create(
        @RequestBody Usuario input
    ) {
        return new ResponseEntity<Usuario>(input, HttpStatus.CREATED);
    }

}
