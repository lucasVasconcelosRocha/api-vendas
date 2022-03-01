package br.com.lrvasconcelos.domain.repository;

import br.com.lrvasconcelos.domain.entity.Cliente;
import br.com.lrvasconcelos.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidosRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);
}
