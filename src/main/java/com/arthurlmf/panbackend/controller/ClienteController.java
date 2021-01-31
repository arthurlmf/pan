package com.arthurlmf.panbackend.controller;

import com.arthurlmf.panbackend.model.Cliente;
import com.arthurlmf.panbackend.model.Endereco;
import com.arthurlmf.panbackend.service.ClienteService;
import com.arthurlmf.panbackend.service.EnderecoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {

    static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteService service;

    @GetMapping(path= "/cliente/v1.0/consultar/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> consultarCliente(
            @PathVariable String cpf) {
        try {
            final Cliente cliente = service.consultarClientePorCpf(cpf);

            if (cliente != null) {
                logger.info("cliente encontrado " + cliente.toString());
                return ResponseEntity.ok(cliente.toString());
            }
        } catch (Exception e) {
            logger.error("Erro: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        }
        logger.info(String.format("cliente de cpf  %s nao encontrado", cpf));
        return ResponseEntity.noContent().build();
    }
}
