package com.arthurlmf.panbackend.service;

import com.arthurlmf.panbackend.exception.PanbackendException;
import com.arthurlmf.panbackend.model.Cliente;
import com.arthurlmf.panbackend.model.Endereco;
import com.arthurlmf.panbackend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EnderecoService enderecoService;

    public Cliente consultarClientePorCpf (String cpf){
        return repository.findByCpf(cpf);
    }

    public Cliente alterarEnderecoDeCliente(Cliente cliente, Endereco novoEndereco) throws PanbackendException {
        if(cliente == null || cliente.getCpf() == null){
            throw new PanbackendException("Cliente invalido");
        }
        Cliente clienteRetornado = consultarClientePorCpf(cliente.getCpf());
        if(clienteRetornado == null){
            throw new PanbackendException("Cliente invalido");
        }


        Endereco enderecoSalvo = enderecoService.salvarEndereco(novoEndereco);
        clienteRetornado.setEndereco(enderecoSalvo);

        return repository.saveAndFlush(clienteRetornado);
    }
}
