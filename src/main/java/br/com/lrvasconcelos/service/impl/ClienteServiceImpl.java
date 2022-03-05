package br.com.lrvasconcelos.service.impl;


import br.com.lrvasconcelos.domain.entity.Cliente;
import br.com.lrvasconcelos.domain.repository.ClientesRepository;
import br.com.lrvasconcelos.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClientesRepository repository;

    @Override
    public Cliente create(Cliente cliente) {
       return repository.save(cliente);
    }

    @Override
    public List<Cliente> findByName(String nome) {
        return repository.findByNomeLike(nome);
    }

    @Override
    public void delete(Cliente cliente) {
        repository.delete(cliente);
    }

    @Override
    public List<Cliente> findByFilters(Example<Cliente> filtros) {
        return repository.findAll(filtros);
    }

    @Override
    public Optional<Cliente> findById(Integer id) {
        return repository.findById(id);
    }
}
