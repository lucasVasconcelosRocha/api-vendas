package br.com.lrvasconcelos.service;

import br.com.lrvasconcelos.domain.entity.Produto;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {

    Produto create(Produto produto);

    List<Produto> listProducts(Example<Produto> filters);

    Optional<Produto> findProductById(Integer id);

    void deleteProduct(Produto produto);

}
