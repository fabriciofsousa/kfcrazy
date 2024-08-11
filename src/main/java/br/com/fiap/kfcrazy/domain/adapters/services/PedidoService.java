package br.com.fiap.kfcrazy.domain.adapters.services;

import br.com.fiap.kfcrazy.infra.adapters.entities.Produto;
import br.com.fiap.kfcrazy.domain.enums.StatusPagamento;
import br.com.fiap.kfcrazy.domain.exceptions.PedidoNaoEncontradoException;
import br.com.fiap.kfcrazy.domain.enums.StatusPedido;
import br.com.fiap.kfcrazy.infra.adapters.entities.Pedido;
import br.com.fiap.kfcrazy.infra.adapters.repository.PedidoRepository;
import br.com.fiap.kfcrazy.domain.ports.PedidoServicePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class PedidoService implements PedidoServicePort {

    private final PedidoRepository pedidoRepository;

    @Override
    public Pedido create(Pedido pedido) {

        pedido.setDataTransacao(LocalDateTime.now());

        pedido.setStatusPagamento(StatusPagamento.PENDENTE);
        //fake checkout
        pedido.setStatusPedido(StatusPedido.EM_PROCESSAMENTO);

        BigDecimal valorTotal = BigDecimal.ZERO;

        for (Produto produto : pedido.getProdutos()) {
            valorTotal = valorTotal.add(produto.getPreco());
        }
        pedido.setValorTotal(valorTotal);
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
    public void delete(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado!"));
        pedidoRepository.delete(pedido);
    }
}