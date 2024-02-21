package com.firstdecision.cadatro.api.api.services;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.firstdecision.cadatro.api.domain.exceptions.NegocioException;
import com.firstdecision.cadatro.api.domain.models.Usuario;

@SpringBootTest
@ContextConfiguration(classes = {
    UsuarioService.class
})
public class UsuarioServiceTest {
 
    final String URL = "/v1/usuarios";
    final String senha = "123456";
    final String confirmacaoSenha = "654321";
    final String email = "pedro@mail";
    final String nome = "Pedro de Alcântara";

    @Autowired
    UsuarioService service;

    @Test
    void deveRetornarObjetoUsuario_QuandoTudoOk() throws Exception {
        var confirmacaoSenha = senha;
        Usuario usuario = new Usuario(nome, email, senha, confirmacaoSenha);
        var actual = service.salvar(usuario);
        assertNotNull(actual);
        assertInstanceOf(Usuario.class, actual);
    }

    @Test
    void deveLancarException_QuandoSenhaAndConfirmacaoSenhaDiferentes() {
        Usuario usuario = new Usuario(nome, email, senha, confirmacaoSenha);
        Executable executable = () -> service.salvar(usuario);
        assertThrows(NegocioException.class, executable);
    }

}
