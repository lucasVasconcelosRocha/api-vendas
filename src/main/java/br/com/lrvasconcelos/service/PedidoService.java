package br.com.lrvasconcelos.service;

import br.com.lrvasconcelos.api.dto.PedidoDTO;
import br.com.lrvasconcelos.domain.entity.Pedido;
import br.com.lrvasconcelos.domain.enums.StatusPedido;

import java.util.Optional;

public interface PedidoService {

    Pedido create(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
