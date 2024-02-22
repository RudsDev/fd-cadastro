package com.firstdecision.cadatro.api.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstdecision.cadatro.api.api.repository.UsuarioRepository;
import com.firstdecision.cadatro.api.domain.exceptions.NegocioException;
import com.firstdecision.cadatro.api.domain.models.Usuario;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository repository;
    
    public Usuario salvar(Usuario usuario) throws NegocioException {
        if (!usuario.isSenhasIguais())
            throw new NegocioException("Senha e confirmação de senha devem ser iguais!");
        return repository.save(usuario);
    }

}
