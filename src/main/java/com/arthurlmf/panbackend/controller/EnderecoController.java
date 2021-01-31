package com.arthurlmf.panbackend.controller;

import com.arthurlmf.panbackend.model.Endereco;
import com.arthurlmf.panbackend.service.EnderecoService;
import com.arthurlmf.panbackend.vo.EstadoVO;
import com.arthurlmf.panbackend.vo.MunicipioVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EnderecoController {

    static final Logger logger = LoggerFactory.getLogger(EnderecoController.class);

    @Autowired
    private EnderecoService service;

    @GetMapping(path= "/endereco/v1.0/consultar/{cep}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> consultarEndereco(
            @PathVariable String cep) {
        try {

            final Endereco enderecoPorCep = service.getEnderecoPorCep(cep);

            if (enderecoPorCep != null) {
                logger.info("cep encontrado " + enderecoPorCep.toString());
                return ResponseEntity.ok(enderecoPorCep.toString());
            }
        } catch (Exception e) {
            logger.error("Erro: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        }
        logger.info(String.format("endereco do cep  %s nao encontrado", cep));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/endereco/v1.0/alterar/")
    public ResponseEntity<String> alterarEndereco(
            Endereco endereco) {
        try {
            service.salvarEndereco(endereco);
            logger.info("endereco salvo " + endereco.toString());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        }
    }

    @GetMapping(path= "/estados/v1.0/consultar/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> consultarEstados() {
        try {
            final List<EstadoVO> estados = service.getEstados();

            if (estados != null) {
                logger.info("estados encontrado " + estados.toString());
                return ResponseEntity.ok(estados.toString());
            }
        } catch (Exception e) {
            logger.error("Erro: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        }
        logger.info(String.format("estados não encontrados"));
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path= "/estados/v1.0/{sigla}/municipios/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> consultarMunicipios( @PathVariable String sigla) {
        try {
            final List<MunicipioVO> municipiosPorEstado = service.getMunicipiosPorEstado(sigla);

            if (municipiosPorEstado != null) {
                logger.info("municipios encontrados " + municipiosPorEstado.toString());
                return ResponseEntity.ok(municipiosPorEstado.toString());
            }
        } catch (Exception e) {
            logger.error("Erro: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        }
        logger.info(String.format("municipios do estado %s não encontrados", sigla));
        return ResponseEntity.noContent().build();
    }
}
