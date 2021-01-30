package com.arthurlmf.panbackend.repository;

import com.arthurlmf.panbackend.model.Cliente;
import com.arthurlmf.panbackend.model.Endereco;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClienteRepositoryTest {

    @Autowired
    ClienteRepository repository;

    @Autowired
    EnderecoRepository enderecoRepository;

    private static  Endereco end1;

    @BeforeEach
    void setUp() {
        end1 = new Endereco();
        end1.setCep("05438-300");
        end1.setLogradouro("Rua Heitor Penteado");
        end1.setComplemento("de 1726 ao fim - lado par");
        end1.setBairro("Sumarezinho");
        end1.setLocalidade("São Paulo");
        end1.setUf("SP");
        end1.setIbge("3550308");
        end1.setGia("1004");
        end1.setDdd("11");
        end1.setSiafi("siafi");

        enderecoRepository.save(end1);
    }

    @Test
    public void testSaveCliente() {

        Cliente cl1 =  new Cliente("1", "Jose", "1345678921", end1);
        Cliente cl2 =  new Cliente("2", "Maria", "2345678922", end1);
        Cliente cl3 =  new Cliente("3", "Raul", "3345678923", end1);


        repository.save(cl1);
        repository.save(cl2);

        List<Cliente> list = repository.findAll();

        assertTrue(list.contains(cl1));
        assertTrue(list.contains(cl2));
        assertFalse(list.contains(cl3));
    }


}