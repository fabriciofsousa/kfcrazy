package br.com.fiap.kfcrazy.domain.ports;

import br.com.fiap.kfcrazy.infra.adapters.entities.Pedido;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface PreparacaoServicePort {
    public Pedido criarPedidoFakeCheckout(Pedido pedido);
    public Pedido enviarParaPreparacao(Long id);
    public Pedido iniciarPreparacao(Long id);
    public Pedido prepararPedido(Long id);
    public Pedido cancelarPedido(Long id);
    public List<Pedido> listarPedidos();
}
