package br.com.fiap.kfcrazy.preparacao.infraestructure.service;

import br.com.fiap.kfcrazy.pedido.domain.model.Pedido;

import java.util.List;

public abstract class PreparacaoService {
    public abstract Pedido criarPedidoFakeCheckout(Pedido pedido);
    public abstract Pedido enviarParaPreparacao(Long id);
    public abstract Pedido iniciarPreparacao(Long id);
    public abstract Pedido prepararPedido(Long id);
    public abstract Pedido cancelarPedido(Long id);
    public abstract List<Pedido> listarPedidos();
}
