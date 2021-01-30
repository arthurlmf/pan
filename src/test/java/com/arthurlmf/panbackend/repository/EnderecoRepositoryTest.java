package com.arthurlmf.panbackend.repository;

import com.arthurlmf.panbackend.model.Cliente;
import com.arthurlmf.panbackend.model.Endereco;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql("/test-data.sql")
class EnderecoRepositoryTest {

    @Autowired
    EnderecoRepository repository;

    @Test
    public void testSaveEndereco() {
        Endereco end1 = new Endereco();
        end1.setCep("05438-300");
        end1.setLogradouro("Rua Heitor Penteado");
        end1.setComplemento("de 1726 ao fim - lado par");
        end1.setBairro("Sumarezinho");
        end1.setLocalidade("São Paulo");
        end1.setUf("SP");
        end1.setIbge("3550308");
        end1.setGia("1004");
        end1.setDdd("11");
        end1.setSiafi("7107");

        List<Endereco> list = repository.findAll();

        assertTrue(list.size() == 1);

        repository.save(end1);


        list = repository.findAll();

        assertTrue(list.contains(end1));
        assertTrue(list.size() == 2);

    }

}