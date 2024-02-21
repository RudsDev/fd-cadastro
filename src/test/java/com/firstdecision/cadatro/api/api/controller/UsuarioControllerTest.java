package com.firstdecision.cadatro.api.api.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firstdecision.cadatro.api.api.controller.v1.UsuarioController;
import com.firstdecision.cadatro.api.domain.models.Usuario;

@SpringBootTest
@ContextConfiguration(classes = {
    UsuarioController.class,
    ObjectMapper.class
})
public class UsuarioControllerTest {

    final String URL = "/v1/usuarios";
    final String senha = "123456";
    final String confirmacaoSenha = "654321";
    final String email = "pedro@mail";
    final String nome = "Pedro de Alcântara";


    MockMvc mvc;
    
    Usuario usuario = new Usuario(nome, email, senha, confirmacaoSenha);

    @Autowired
    ObjectMapper mapper;

    @Autowired
    UsuarioController controller;

    @BeforeEach
    public void setup() { 
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void deveRetornarStatus201ComValoresCorretos_QuandoCriarUsuarioCorretamente() throws Exception {
        String json = mapper.writeValueAsString(usuario);

        mvc.perform(
            post(URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json)
            .characterEncoding("utf-8")
        )
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.nome", is(usuario.getNome()))) 
        .andExpect(jsonPath("$.email", is(usuario.getEmail())));
    }

    @Test
    void deveRetornarStatus201SemSenhas_QuandoCriarUsuarioCorretamente() throws Exception {
        String json = mapper.writeValueAsString(usuario);
        mvc.perform(
            post(URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json)
            .characterEncoding("utf-8")
        )
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.senha").doesNotExist()) 
        .andExpect(jsonPath("$.confirmacaoSenha").doesNotExist())
        .andExpect(jsonPath("$.senhasIguais").doesNotExist());
    }

    @Test
    void deveLancarException_QuandoCriarUsuarioNomeStringNull() throws Exception {
        String nome = null;
        validaValorNome(nome);
    }

    @Test
    void deveLancarException_QuandoCriarUsuarioNomeStringVazia() throws Exception {
        String nome = "";
        validaValorNome(nome);
    }

    @Test
    void deveLancarException_QuandoCriarUsuarioNomeStringEspacoBranco() throws Exception {
        String nome = " ";
        validaValorNome(nome);
    }

    @Test
    void deveLancarException_QuandoCriarUsuarioNomeStringMenorQueTres() throws Exception {
        String nome = "Zé";
        validaValorNome(nome);
    }

    @Test
    void deveLancarException_QuandoCriarUsuarioNomeStringMaiorQueCinquenta() throws Exception {
        String nome = "Pedro de Alcântara João Carlos Leopoldo Salvador Bibiano Francisco Xavier de Paula Leocádio Miguel Gabriel Rafael Gonzaga";
        validaValorNome(nome);
    }

    @Test
    void deveLancarException_QuandoEmailEspacoEmBranco() throws Exception {
        String email = " ";
        validaValorEmail(email);
    }

    @Test
    void deveLancarException_QuandoEmailStringNull() throws Exception {
        String email = null;
        validaValorEmail(email);
    }

    @Test
    void deveLancarException_QuandoEmailInvalido() throws Exception {
        String email = "p@dro@mail";
        validaValorEmail(email);
    }

    @Test
    void deveLancarException_QuandoSenhaStringVazia() throws Exception {
        String senha = "";
        validaValorSenha(senha);
    }

    @Test
    void deveLancarException_QuandoSenhaEspacoEmBranco() throws Exception {
        String senha = " ";
        validaValorSenha(senha);
    }

    /* @Test
    void deveLancarException_QuandoSenhaStringNull() throws Exception {
        String senha = null;
        validaValorSenha(senha);
    } */

    @Test
    void deveLancarException_QuandoSenhaStringMenorQueSeis() throws Exception {
        String senha = "123";
        validaValorSenha(senha);
    }

    @Test
    void deveLancarException_QuandoSenhaStringMaiorQueVinte() throws Exception {
        String senha = "Uma senha que é maior que vinte caracteres";
        validaValorSenha(senha);
    }

    private void validaValorNome(String nome) throws JsonProcessingException, Exception {
        var usuario = new Usuario(nome, email, senha, confirmacaoSenha);
        String json = mapper.writeValueAsString(usuario);
        var result = mvc.perform(
            post(URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json)
            .characterEncoding("utf-8")
        )
        .andDo(print())
        .andReturn();
        Exception exception = result.getResolvedException();
        assertNotNull(exception);
        assertEquals(Response.SC_BAD_REQUEST, result.getResponse().getStatus());
    }

    private void validaValorEmail(String email) throws JsonProcessingException, Exception {
        var usuario = new Usuario(nome, email, senha, confirmacaoSenha);
        String json = mapper.writeValueAsString(usuario);
        var result = mvc.perform(
            post(URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json)
            .characterEncoding("utf-8")
        )
        .andDo(print())
        .andReturn();
        Exception exception = result.getResolvedException();
        assertNotNull(exception);
        assertEquals(Response.SC_BAD_REQUEST, result.getResponse().getStatus());
    }

    private void validaValorSenha(String senha) throws JsonProcessingException, Exception {
        var usuario = new Usuario(nome, email, senha, confirmacaoSenha);
        String json = mapper.writeValueAsString(usuario);
        var result = mvc.perform(
            post(URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json)
            .characterEncoding("utf-8")
        )
        .andDo(print())
        .andReturn();
        Exception exception = result.getResolvedException();
        assertNotNull(exception);
        assertEquals(Response.SC_BAD_REQUEST, result.getResponse().getStatus());
    }

}
