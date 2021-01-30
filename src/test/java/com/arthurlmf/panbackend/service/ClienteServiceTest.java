package com.arthurlmf.panbackend.service;

import com.arthurlmf.panbackend.exception.PanbackendException;
import com.arthurlmf.panbackend.model.Cliente;
import com.arthurlmf.panbackend.model.Endereco;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql("/test-data.sql")
class ClienteServiceTest {

    @Autowired
    ClienteService service;

    @Test
    void consultarClientePorCpf_comSucesso() {
        final Cliente cliente = service.consultarClientePorCpf("99999999999");
        Assertions.assertNotNull(cliente);
    }

    @Test
    void consultarClientePorCpf_naoEncontrado() {
        final Cliente cliente = service.consultarClientePorCpf("11111111111");
        Assertions.assertNull(cliente);
    }

    @Test
    void alterarEnderecoDeCliente_comSucesso() {
        Endereco novoEndereco = new Endereco();
        novoEndereco.setCep("05438-300");
        novoEndereco.setLogradouro("Rua Heitor Penteado");
        novoEndereco.setComplemento("de 1726 ao fim - lado par");
        novoEndereco.setBairro("Sumarezinho");
        novoEndereco.setLocalidade("São Paulo");
        novoEndereco.setUf("SP");
        novoEndereco.setIbge("3550308");
        novoEndereco.setGia("1004");
        novoEndereco.setDdd("11");
        novoEndereco.setSiafi("7107");

        final Cliente cliente = service.consultarClientePorCpf("99999999999");

        Cliente clienteRetornado  = null;
        try {
            clienteRetornado = service.alterarEnderecoDeCliente(cliente, novoEndereco);
            Assertions.assertEquals(clienteRetornado.getEndereco().getCep(),novoEndereco.getCep());
        } catch (PanbackendException e) {
            fail();
        }
    }

    @Test
    void alterarEnderecoDeCliente_comErro_enderecoInvalido() {
        Endereco novoEndereco = new Endereco();
        novoEndereco.setCep("1");

        final Cliente cliente = service.consultarClientePorCpf("99999999999");

        try {
            Cliente clienteRetornado = service.alterarEnderecoDeCliente(cliente, novoEndereco);
            fail();
        } catch (PanbackendException e1) {
            Assertions.assertEquals(e1.getMessage(), "Endereço invalido");
        }
    }

    @Test
    void alterarEnderecoDeCliente_comErro_clienteInvalidoNulo() {
        Endereco novoEndereco = new Endereco();
        novoEndereco.setCep("1");

        final Cliente cliente = service.consultarClientePorCpf("11111111111");

        try {
            Cliente clienteRetornado = service.alterarEnderecoDeCliente(cliente, novoEndereco);
            fail();
        } catch (PanbackendException e1) {
            Assertions.assertEquals(e1.getMessage(), "Cliente invalido");
        }

    }

    @Test
    void alterarEnderecoDeCliente_comErro_clienteInvalido() {
        Endereco novoEndereco = new Endereco();
        novoEndereco.setCep("1");

        final Cliente cliente = new Cliente();
        cliente.setCpf("9999");

        try {
            Cliente clienteRetornado = service.alterarEnderecoDeCliente(cliente, novoEndereco);
            fail();
        } catch (PanbackendException e1) {
            Assertions.assertEquals(e1.getMessage(), "Cliente invalido");
        }

    }

}