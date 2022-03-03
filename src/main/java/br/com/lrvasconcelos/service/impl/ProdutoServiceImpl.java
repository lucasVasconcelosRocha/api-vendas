package br.com.lrvasconcelos.service.impl;

import br.com.lrvasconcelos.domain.entity.Produto;
import br.com.lrvasconcelos.domain.repository.ProdutosRepository;
import br.com.lrvasconcelos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutosRepository repository;

    @Override
    public Produto create(Produto produto) {
        return repository.save(produto);
    }

    @Override
    public List<Produto> listProducts(Example filters) {
        return repository.findAll(filters);
    }

    @Override
    public Optional<Produto> findProductById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void deleteProduct(Produto produto) {
        repository.delete(produto);
    }

}
