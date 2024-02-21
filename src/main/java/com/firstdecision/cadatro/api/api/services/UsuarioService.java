package com.firstdecision.cadatro.api.api.services;

import com.firstdecision.cadatro.api.domain.exceptions.NegocioException;
import com.firstdecision.cadatro.api.domain.models.Usuario;

public class UsuarioService {
    
    public Usuario salvar(Usuario usuario) throws NegocioException {
        if (!usuario.isSenhasIguais())
            throw new NegocioException("Senha e confirmação de senha devem ser iguais!");
        return usuario;
    }

}
