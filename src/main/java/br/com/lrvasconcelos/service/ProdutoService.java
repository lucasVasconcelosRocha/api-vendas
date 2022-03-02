package br.com.lrvasconcelos.service;

import br.com.lrvasconcelos.domain.entity.Produto;
import br.com.lrvasconcelos.domain.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutosRepository repository;

    public Produto create(Produto produto) {
        return repository.save(produto);
    }

    public List<Produto> listProducts(Example filters) {
        return repository.findAll(filters);
    }

    public Optional<Produto> findProductById(Integer id) {
        return repository.findById(id);
    }

    public void deleteProduct(Produto produto) {
        repository.delete(produto);
    }

}
