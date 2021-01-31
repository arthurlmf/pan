package com.arthurlmf.panbackend.service;

import com.arthurlmf.panbackend.controller.ClienteController;
import com.arthurlmf.panbackend.exception.PanbackendException;
import com.arthurlmf.panbackend.model.Cliente;
import com.arthurlmf.panbackend.model.Endereco;
import com.arthurlmf.panbackend.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ClienteService {

    static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EnderecoService enderecoService;

    public Cliente consultarClientePorCpf (String cpf){
        return repository.findByCpf(cpf);
    }

    public Cliente alterarEnderecoDeCliente(Cliente cliente, Endereco novoEndereco) throws PanbackendException {
        if(cliente == null || cliente.getCpf() == null){
            logger.error("parametros invalido");
            throw new PanbackendException("Cliente invalido");
        }
        Cliente clienteRetornado = consultarClientePorCpf(cliente.getCpf());
        if(clienteRetornado == null){
            logger.error("cliente retornado é null");
            throw new PanbackendException("Cliente invalido");
        }
        Endereco enderecoSalvo = enderecoService.salvarEndereco(novoEndereco);
        logger.info("endereco salvo " + enderecoSalvo.toString());
        clienteRetornado.setEndereco(enderecoSalvo);

        logger.info("endereco do cliente alterado " + enderecoSalvo.toString());
        return repository.saveAndFlush(clienteRetornado);
    }
}
