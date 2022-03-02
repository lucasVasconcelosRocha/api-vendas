package br.com.lrvasconcelos.service;


import br.com.lrvasconcelos.domain.entity.Cliente;
import br.com.lrvasconcelos.domain.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClientesRepository repository;

    public Cliente create(Cliente cliente) {
       return repository.save(cliente);
    }

    public List<Cliente> findByName(String nome) {
        return repository.findByNomeLike(nome);
    }

    public void delete(Cliente cliente) {
        repository.delete(cliente);
    }

    public List<Cliente> findByFilters(Example filtros) {
        return repository.findAll(filtros);
    }

    public Optional<Cliente> findById(Long id) {
        return repository.findById(id);
    }
}
