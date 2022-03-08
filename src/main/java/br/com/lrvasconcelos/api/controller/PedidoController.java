package br.com.lrvasconcelos.api.controller;

import br.com.lrvasconcelos.api.dto.InformacaoItemPedidoDTO;
import br.com.lrvasconcelos.api.dto.InformacoesPedidoDTO;
import br.com.lrvasconcelos.api.dto.PedidoDTO;
import br.com.lrvasconcelos.api.dto.UpdateStatusPedidoDTO;
import br.com.lrvasconcelos.domain.entity.ItemPedido;
import br.com.lrvasconcelos.domain.entity.Pedido;
import br.com.lrvasconcelos.domain.enums.StatusPedido;
import br.com.lrvasconcelos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid PedidoDTO dto) {
        Pedido pedido = service.create(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id) {
        return service
                .obterPedidoCompleto(id)
                .map(this::convertInfoPedido)
                .orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,"Pedido não encontrado"));
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id,
                             @RequestBody UpdateStatusPedidoDTO dto) {

        String novoStatus = dto.getNovoStatus();
        service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }

    private InformacoesPedidoDTO convertInfoPedido(Pedido pedido) {
        return InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .nomeCliente(pedido.getCliente().getNome())
                .cpf(pedido.getCliente().getCpf())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .itens(convertInfoItemPedido(pedido.getItens()))
                .build();
    }

    private List<InformacaoItemPedidoDTO> convertInfoItemPedido(List<ItemPedido> itens) {
        if(CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }

        return itens.stream().map(
                item -> InformacaoItemPedidoDTO
                        .builder()
                        .descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()
        ).collect(Collectors.toList());
    }
}
