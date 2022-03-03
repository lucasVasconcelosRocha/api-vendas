package br.com.lrvasconcelos.service;

import br.com.lrvasconcelos.api.controller.dto.PedidoDTO;
import br.com.lrvasconcelos.domain.entity.Pedido;

public interface PedidoService {

    Pedido create(PedidoDTO dto);
}
