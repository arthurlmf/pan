package com.arthurlmf.panbackend.service;

import com.arthurlmf.panbackend.exception.PanbackendException;
import com.arthurlmf.panbackend.model.Endereco;
import com.arthurlmf.panbackend.repository.EnderecoRepository;
import com.arthurlmf.panbackend.vo.EstadoVO;
import com.arthurlmf.panbackend.vo.MunicipioVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class EnderecoService {

    static final Logger logger = LoggerFactory.getLogger(EnderecoService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EnderecoRepository repository;

    public Endereco getEnderecoPorCep(String cep)
    {
        final String uri = "https://viacep.com.br/ws/"+cep +"/json";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Endereco> response = restTemplate.getForEntity(uri, Endereco.class);
        return response.getBody();
    }

    public List<EstadoVO> getEstados()
    {
        final String uri = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<EstadoVO[]> response = restTemplate.getForEntity(uri, EstadoVO[].class);
        logger.info("Api estados retornou salvo " + response.toString());

        LinkedList<EstadoVO> list = new LinkedList<EstadoVO>(Arrays.asList(response.getBody()));
        EstadoVO sp = list.get(list.indexOf(new EstadoVO("SP")));
        EstadoVO rj = list.get(list.indexOf(new EstadoVO("RJ")));
        list.remove(sp);
        list.remove(rj);
        list.sort((e1, e2) -> e1.getNome().compareTo(e2.getNome()));
        list.addFirst(rj);
        list.addFirst(sp);

        return list;
    }

    public List<MunicipioVO> getMunicipiosPorEstado(String estado)
    {
        final String uri = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/"+ estado+"/municipios";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<MunicipioVO[]> response = restTemplate.getForEntity(uri, MunicipioVO[].class);

        logger.info("Api municipios retornou salvo " + response.toString());

        return Arrays.asList(response.getBody());
    }

    public Endereco salvarEndereco(Endereco endereco) throws PanbackendException {
        try {
            Endereco enderecoConsultado = getEnderecoPorCep(endereco.getCep());
            if(enderecoConsultado == null){
                logger.error("Endereco invalido");
                throw new PanbackendException("Endereço invalido");
            }
        }catch (HttpClientErrorException e){
            logger.error("Endereco invalido");
            throw new PanbackendException("Endereço invalido");
        }

        logger.info("endereco salvo " + endereco.toString());
        return repository.save(endereco);
    }
}
