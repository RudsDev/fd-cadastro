package com.firstdecision.cadatro.api.api.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.firstdecision.cadatro.api.api.controller.v1.UsuarioController;
import com.firstdecision.cadatro.api.domain.models.Usuario;

@ExtendWith(SpringExtension.class)
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

}
