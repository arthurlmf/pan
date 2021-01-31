package com.arthurlmf.panbackend.controller;

import com.arthurlmf.panbackend.model.Cliente;
import com.arthurlmf.panbackend.model.Endereco;
import com.arthurlmf.panbackend.service.ClienteService;
import com.arthurlmf.panbackend.service.EnderecoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void consultarClienteComSucesso() throws Exception {

        Endereco enderecoEsperado = new Endereco();
        enderecoEsperado.setCep("01310-100");
        enderecoEsperado.setLogradouro("Avenida Paulista");
        enderecoEsperado.setComplemento("de 612 a 1510 - lado par");
        enderecoEsperado.setBairro("Bela Vista");
        enderecoEsperado.setLocalidade("São Paulo");
        enderecoEsperado.setUf("SP");
        enderecoEsperado.setIbge("3550308");
        enderecoEsperado.setGia("1004");
        enderecoEsperado.setDdd("11");
        enderecoEsperado.setSiafi("7107");

        Cliente clienteEsperado =  new Cliente("99", "Conceicao Silva", "99999999999", enderecoEsperado);

        final String cpf = "99999999999";
        when(service.consultarClientePorCpf(cpf)).thenReturn(clienteEsperado);

        this.mockMvc.perform(get("/cliente/v1.0/consultar/{cpf}", cpf)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString("Cliente{id='99', Nome='Conceicao Silva', cpf='99999999999'")));
    }

    @Test
    public void consultarCliente_NaoEncontrado() throws Exception {
        final String cpf = "111";
        when(service.consultarClientePorCpf(cpf)).thenReturn(null);

        this.mockMvc.perform(get("/cliente/v1.0/consultar/{cpf}", cpf)
        ).andDo(print())
                .andExpect(status().isNoContent());
    }




}