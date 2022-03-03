package br.com.lrvasconcelos.service;

import br.com.lrvasconcelos.domain.entity.Cliente;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    Cliente create(Cliente cliente);

    List<Cliente> findByName(String nome);

    void delete(Cliente cliente);

    List<Cliente> findByFilters(Example filtros);

    Optional<Cliente> findById(Integer id);
}
