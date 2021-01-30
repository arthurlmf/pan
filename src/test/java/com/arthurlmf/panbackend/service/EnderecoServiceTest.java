package com.arthurlmf.panbackend.service;

import com.arthurlmf.panbackend.model.Cliente;
import com.arthurlmf.panbackend.model.Endereco;
import com.arthurlmf.panbackend.vo.EstadoVO;
import com.arthurlmf.panbackend.vo.MunicipioVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EnderecoServiceTest {

    @Autowired
    EnderecoService service;

    @Test
    void consultarEnderecoPorCep_comSucesso() {
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

        final Endereco enderecoRetornado = service.getEnderecoPorCep("05438-300");
        assertEquals(enderecoEsperado, enderecoRetornado);
    }

    @Test
    void consultarEstados_comSucesso() {

        List<EstadoVO> list = service.getEstados();

        assertTrue(list.size() == 27);
        assertEquals(list.get(0), new EstadoVO("SP"));
        assertEquals(list.get(1), new EstadoVO("RJ"));
        assertEquals(list.get(2), new EstadoVO("AC"));
    }

    @Test
    void consultarMunicipiosPorEstado_comSucesso() {

        List<MunicipioVO> list = service.getMunicipiosPorEstado("SP");

        assertTrue(list.contains(new MunicipioVO("Adamantina")));
        assertTrue(list.contains(new MunicipioVO("Adolfo")));
        assertTrue(list.contains(new MunicipioVO("Aguaí")));
    }

}