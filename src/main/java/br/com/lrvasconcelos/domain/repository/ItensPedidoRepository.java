package br.com.lrvasconcelos.domain.repository;

import br.com.lrvasconcelos.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensPedidoRepository extends JpaRepository<ItemPedido, Integer> {
}
