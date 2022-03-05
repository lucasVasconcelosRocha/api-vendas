package br.com.lrvasconcelos.service.impl;

import br.com.lrvasconcelos.api.dto.ItemPedidoDTO;
import br.com.lrvasconcelos.api.dto.PedidoDTO;
import br.com.lrvasconcelos.domain.entity.Cliente;
import br.com.lrvasconcelos.domain.entity.ItemPedido;
import br.com.lrvasconcelos.domain.entity.Pedido;
import br.com.lrvasconcelos.domain.entity.Produto;
import br.com.lrvasconcelos.domain.repository.ClientesRepository;
import br.com.lrvasconcelos.domain.repository.ItensPedidoRepository;
import br.com.lrvasconcelos.domain.repository.PedidosRepository;
import br.com.lrvasconcelos.domain.repository.ProdutosRepository;
import br.com.lrvasconcelos.exceptions.RegraNegocioException;
import br.com.lrvasconcelos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidosRepository pedidosRepository;
    @Autowired
    private ClientesRepository clientesRepository;
    @Autowired
    private ProdutosRepository produtosRepository;
    @Autowired
    private ItensPedidoRepository itensPedidoRepository;

    @Override
    @Transactional
    public Pedido create(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Cliente é inválido: " + idCliente));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());

        List<ItemPedido> itensPedido = convertItens(pedido, dto.getItens());
        pedidosRepository.save(pedido);
        itensPedidoRepository.saveAll(itensPedido);
        pedido.setItens(itensPedido);
        return pedido;
    }

    private List<ItemPedido> convertItens(Pedido pedido, List<ItemPedidoDTO> itens) {
        if(itens.isEmpty()) {
            throw new RegraNegocioException("Não existe itens para a realização do pedido!");
        }

        return itens
                .stream()
                .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(() -> new RegraNegocioException("Produto inválido: " + idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
