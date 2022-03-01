package br.com.lrvasconcelos.domain.repository;

import br.com.lrvasconcelos.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<Produto, Integer> {
}
