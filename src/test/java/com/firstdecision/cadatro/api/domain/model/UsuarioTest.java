package com.firstdecision.cadatro.api.domain.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.firstdecision.cadatro.api.domain.models.Usuario;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class UsuarioTest {

    private Validator validator;

    final String senha = "123456";
    final String confirmacaoSenha = "654321";
    final String email = "pedro@mail";
    final String nome = "Pedro de Alcântara";
    final String nomeGrande = "Pedro de Alcântara João Carlos Leopoldo Salvador Bibiano Francisco Xavier de Paula Leocádio Miguel Gabriel Rafael Gonzaga";
    

    @BeforeEach
    void setUp() {
        var factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void deveRetonarFalse_QuandoSenhasDiferentes() {
        var usuario = new Usuario(nome, email, senha, confirmacaoSenha);
        boolean actual = usuario.isSenhasIguais();
        assertFalse(actual);
    }

    @Test
    void deveRetonarTrue_QuandoSenhasIguais() {
        String confirmacaoSenha = senha;
        var usuario = new Usuario(nome, email, senha, confirmacaoSenha);
        boolean actual = usuario.isSenhasIguais();
        assertTrue(actual);
    }

    @Test
    void deveConterValidacoes_QuandoSenhaStringVazia() {
        String senha = "";
        var usuario = new Usuario(nome, email, senha, confirmacaoSenha);
        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        assertFalse(violations.isEmpty());
    }

    @Test
    void deveConterValidacoes_QuandoSenhaEspacoEmBranco() {
        String senha = " ";
        var usuario = new Usuario(nome, email, senha, confirmacaoSenha);
        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        assertFalse(violations.isEmpty());
    }

    @Test
    void deveConterValidacoes_QuandoSenhaStringNull() {
        String senha = null;
        var usuario = new Usuario(nome, email, senha, confirmacaoSenha);
        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        assertFalse(violations.isEmpty());
    }

    @Test
    void deveConterValidacoes_QuandoSenhaStringMenorQueSeis() {
        String senha = "123";
        var usuario = new Usuario(nome, email, senha, confirmacaoSenha);
        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        assertFalse(violations.isEmpty());
    }

    @Test
    void deveConterValidacoes_QuandoSenhaStringMaiorQueVinte() {
        String senha = "Uma senha que é maior que vinte caracteres";
        var usuario = new Usuario(nome, email, senha, confirmacaoSenha);
        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        assertFalse(violations.isEmpty());
    }

    @Test
    void deveConterValidacoes_QuandoNomeStringVazia() {
        String nome = "";
        var usuario = new Usuario(nome, email, senha, confirmacaoSenha);
        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        assertFalse(violations.isEmpty());
    }

    @Test
    void deveConterValidacoes_QuandoNomeStringEspacoEmBranco() {
        String nome = " ";
        var usuario = new Usuario(nome, email, senha, confirmacaoSenha);
        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        assertFalse(violations.isEmpty());
    }

    @Test
    void deveConterValidacoes_QuandoNomeStringNull() {
        String nome = " ";
        var usuario = new Usuario(nome, email, senha, confirmacaoSenha);
        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        assertFalse(violations.isEmpty());
    }

    @Test
    void deveConterValidacoes_QuandoNomeStringMenorQueTres() {
        String nome = "Zé";
        var usuario = new Usuario(nome, email, senha, confirmacaoSenha);
        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        assertFalse(violations.isEmpty());
    }

    @Test
    void deveConterValidacoes_QuandoNomeStringMaiorQueCinquenta() {
        String nome = "Pedro de Alcântara João Carlos Leopoldo Salvador Bibiano Francisco Xavier de Paula Leocádio Miguel Gabriel Rafael Gonzaga";
        var usuario = new Usuario(nome, email, senha, confirmacaoSenha);
        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        assertFalse(violations.isEmpty());
    }

    @Test
    void deveConterValidacoes_QuandoEmailStringVazia() {
        String nome = "Pedro de Alcântara";
        String email = "";
        var usuario = new Usuario(nome, email, senha, confirmacaoSenha);
        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        assertFalse(violations.isEmpty());
    }

    @Test
    void deveConterValidacoes_QuandoEmailEspacoEmBranco() {
        String email = " ";
        var usuario = new Usuario(nome, email, senha, confirmacaoSenha);
        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        assertFalse(violations.isEmpty());
    }

    @Test
    void deveConterValidacoes_QuandoEmailStringNull() {
        String email = null;
        var usuario = new Usuario(nome, email, senha, confirmacaoSenha);
        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        assertFalse(violations.isEmpty());
    }

    @Test
    void deveConterValidacoes_QuandoEmailInvalido() {
        String email = "p@dro@mail";
        var usuario = new Usuario(nome, email, senha, confirmacaoSenha);
        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        assertFalse(violations.isEmpty());
    }

    @Test
    void deveNaoConterValidacoes_QuandoEmailValido() {
        var usuario = new Usuario(nome, email, senha, confirmacaoSenha);
        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        assertTrue(violations.isEmpty());
    }
}
