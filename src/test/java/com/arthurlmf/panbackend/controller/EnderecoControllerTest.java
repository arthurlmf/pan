package com.arthurlmf.panbackend.controller;

import com.arthurlmf.panbackend.model.Cliente;
import com.arthurlmf.panbackend.model.Endereco;
import com.arthurlmf.panbackend.service.ClienteService;
import com.arthurlmf.panbackend.service.EnderecoService;
import com.arthurlmf.panbackend.vo.EstadoVO;
import com.arthurlmf.panbackend.vo.MunicipioVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(EnderecoController.class)
class EnderecoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnderecoService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void consultarEnderecoComSucesso() throws Exception {
        Endereco enderecoEsperado = new Endereco();
        enderecoEsperado.setCep("05438-300");
        enderecoEsperado.setLogradouro("Rua Heitor Penteado");
        enderecoEsperado.setComplemento("de 1726 ao fim - lado par");
        enderecoEsperado.setBairro("Sumarezinho");
        enderecoEsperado.setLocalidade("São Paulo");
        enderecoEsperado.setUf("SP");
        enderecoEsperado.setIbge("3550308");
        enderecoEsperado.setGia("1004");
        enderecoEsperado.setDdd("11");
        enderecoEsperado.setSiafi("7107");


        final String cep = "05438300";
        when(service.getEnderecoPorCep(cep)).thenReturn(enderecoEsperado);

        this.mockMvc.perform(get("/endereco/v1.0/consultar/{cep}", cep)
            ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString("cep='05438-300', logradouro='Rua Heitor Penteado'")));
    }

    @Test
    public void consultarEndereco_EnderecoNaoEncontrado() throws Exception {
        final String cep = "111";
        when(service.getEnderecoPorCep(cep)).thenReturn(null);

        this.mockMvc.perform(get("/endereco/v1.0/consultar/{cep}", cep)
            ).andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void atualizarEnderecoComSucesso() throws Exception {
        Endereco enderecoEsperado = new Endereco();
        enderecoEsperado.setCep("05438-300");
        enderecoEsperado.setLogradouro("Rua Heitor Penteado");
        enderecoEsperado.setComplemento("de 1726 ao fim - lado par");
        enderecoEsperado.setBairro("Sumarezinho");
        enderecoEsperado.setLocalidade("São Paulo");
        enderecoEsperado.setUf("SP");
        enderecoEsperado.setIbge("3550308");
        enderecoEsperado.setGia("1004");
        enderecoEsperado.setDdd("11");
        enderecoEsperado.setSiafi("7107");

        when(service.salvarEndereco(enderecoEsperado)).thenReturn(enderecoEsperado);

        this.mockMvc.perform(put("/endereco/v1.0/alterar/")
                .param("endereco", String.valueOf(enderecoEsperado))
        ).andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void consultarEstados_NaoEncontrado() throws Exception {

        when(service.getEstados()).thenReturn(null);

        this.mockMvc.perform(get("/estados/v1.0/consultar/")
        ).andDo(print())
                .andExpect(status().isNoContent());
    }


    @Test
    public void consultarEstados_comSucesso() throws Exception {
        EstadoVO sp = new EstadoVO(35, "SP", "Sao Paulo" );
        EstadoVO rj = new EstadoVO(33, "RJ", "Rio de Janeiro" );
        EstadoVO ac = new EstadoVO(13, "AC", "Acre" );

        LinkedList<EstadoVO> estadosEsperados = new LinkedList<EstadoVO>();
        estadosEsperados.addFirst(ac);
        estadosEsperados.addFirst(rj);
        estadosEsperados.addFirst(sp);

        when(service.getEstados()).thenReturn(estadosEsperados);

        this.mockMvc.perform(get("/estados/v1.0/consultar/")
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString("EstadoVO{id=35, sigla='SP', nome='Sao Paulo'}, EstadoVO{id=33, sigla='RJ', nome='Rio de Janeiro'}, EstadoVO{id=13, sigla='AC', nome='Acre'")));
    }

    @Test
    public void consultarMunicipios_NaoEncontrado() throws Exception {

        final String sigla = "SS";
        List<MunicipioVO> list = new LinkedList<MunicipioVO>();
        when(service.getMunicipiosPorEstado(sigla)).thenReturn((list));

        this.mockMvc.perform(get("/estados/v1.0/{sigla}/municipios/", sigla)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("[]"));
    }

    @Test
    public void consultarMunicipios_comSucesso() throws Exception {
        final String sigla = "SP";
        List<MunicipioVO> list = new LinkedList<MunicipioVO>();
        list.add(new MunicipioVO("Adamantina"));

        when(service.getMunicipiosPorEstado(sigla)).thenReturn((list));

        this.mockMvc.perform(get("/estados/v1.0/{sigla}/municipios/", sigla)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString("Adamantina")));
    }
}