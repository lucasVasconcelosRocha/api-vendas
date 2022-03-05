package br.com.lrvasconcelos.api.controller;

import br.com.lrvasconcelos.api.dto.PedidoDTO;
import br.com.lrvasconcelos.domain.entity.Pedido;
import br.com.lrvasconcelos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO dto) {
        Pedido pedido = service.create(dto);
        return pedido.getId();
    }
}
