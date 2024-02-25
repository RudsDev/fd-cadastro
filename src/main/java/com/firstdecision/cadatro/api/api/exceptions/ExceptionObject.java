package com.firstdecision.cadatro.api.api.exceptions;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.Instant;

@JsonInclude(Include.NON_NULL)
class ExceptionObject {

    private Integer status;
    private Instant dataHora;
    private String erro;
    private List<Campo> campos;

    

    public ExceptionObject(Integer status, String erro, Instant dataHora, List<Campo> campos) {
        this.status = status;
        this.erro = erro;
        this.dataHora = dataHora;
        this.campos = campos;
    }

    public Instant getDataHora() {
        return dataHora;
    }

    public List<Campo> getCampos() {
        return campos;
    }

    public String getMessage() {
        return erro;
    }
    
    public int getError() {
        return status;
    }

    public static class Campo {
        private String nome;
        private String mensagem;
        
        public Campo(String nome, String mensagem) {
            this.nome = nome;
            this.mensagem = mensagem;
        }

        public String getNome() {
            return nome;
        }
        public String getMensagem() {
            return mensagem;
        }
    }

}
