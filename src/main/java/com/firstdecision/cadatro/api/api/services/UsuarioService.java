package com.firstdecision.cadatro.api.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstdecision.cadatro.api.api.repository.UsuarioRepository;
import com.firstdecision.cadatro.api.core.internationalization.Translator;
import com.firstdecision.cadatro.api.domain.exceptions.ValidacaoException;
import com.firstdecision.cadatro.api.domain.models.Usuario;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository repository;
    
    public Usuario salvar(Usuario usuario) {
        if (!usuario.isSenhasIguais())
            throw new ValidacaoException("Senha", Translator.toLocale("erro_confirmar_senha"));
        return repository.save(usuario);
    }

}
