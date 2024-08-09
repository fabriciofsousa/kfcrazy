package br.com.fiap.kfcrazy.pedido.infrastructure.service.impl;

import br.com.fiap.kfcrazy.Produto.domain.model.Produto;
import br.com.fiap.kfcrazy.cliente.infraestructure.repository.ClienteRepository;
import br.com.fiap.kfcrazy.pagamento.domain.Enum.StatusPagamento;
import br.com.fiap.kfcrazy.pedido.application.exception.PedidoNaoEncontradoException;
import br.com.fiap.kfcrazy.pedido.domain.Enum.StatusPedido;
import br.com.fiap.kfcrazy.pedido.domain.model.Pedido;
import br.com.fiap.kfcrazy.pedido.infrastructure.repository.PedidoRepository;
import br.com.fiap.kfcrazy.pedido.infrastructure.service.PedidoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PedidoServiceImpl implements PedidoService {

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