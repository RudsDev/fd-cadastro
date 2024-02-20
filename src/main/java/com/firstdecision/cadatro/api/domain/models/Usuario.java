package com.firstdecision.cadatro.api.domain.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Usuario {
    
    @NotBlank
    @Size(min = 3, max = 50)
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    private String senha;
    private String confirmacaoSenha;
    
    public Usuario(String senha, String confirmacaoSenha) {
        this.senha = senha;
        this.confirmacaoSenha = confirmacaoSenha;
    }

    public Usuario(String nome, String senha, String confirmacaoSenha) {
        this.nome = nome;
        this.senha = senha;
        this.confirmacaoSenha = confirmacaoSenha;
    }

    public Usuario(String nome, String email, String senha, String confirmacaoSenha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.confirmacaoSenha = confirmacaoSenha;
    }

    Usuario(){}

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

    public boolean isSenhasIguais() {
        return senha.equals(confirmacaoSenha);
    }
   
}
