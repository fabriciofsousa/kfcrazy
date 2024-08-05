package br.com.fiap.kfcrazy.pedido.infrastructure.service.impl;

import br.com.fiap.kfcrazy.pedido.application.exception.PedidoNaoEncontradoException;
import br.com.fiap.kfcrazy.pedido.domain.model.Pedido;
import br.com.fiap.kfcrazy.pedido.infrastructure.repository.PedidoRepository;
import br.com.fiap.kfcrazy.pedido.infrastructure.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public Pedido create(Pedido pedido) {
        pedido.setDataTransacao(LocalDateTime.now());
        return pedidoRepository.save(pedido);
    }

    @Override
    public List<Pedido> getAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido findById(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException("Não encontrado pedido de ID " + id));
    }

    @Override
    public Pedido update(Long id, Pedido pedidoRequest) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado!"));

        pedido.setCliente(pedidoRequest.getCliente());
        pedido.setProdutos(pedidoRequest.getProdutos());
        pedido.setValorTotal(pedidoRequest.getValorTotal());
        pedido.setStatusPagamento(pedidoRequest.getStatusPagamento());
        pedido.setTipo(pedidoRequest.getTipo());
        pedido.setStatusPedido(pedidoRequest.getStatusPedido());
        pedido.setPagamento(pedidoRequest.getPagamento());

        return pedidoRepository.save(pedido);
    }

    @Override
    public void delete(Long id) {
        pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado!"));
        pedidoRepository.deleteById(id);
    }
}