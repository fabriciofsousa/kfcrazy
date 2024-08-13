package br.com.fiap.kfcrazy.domain.adapters.services;

import br.com.fiap.kfcrazy.domain.enums.StatusPagamento;
import br.com.fiap.kfcrazy.domain.enums.StatusPedido;
import br.com.fiap.kfcrazy.infra.adapters.entities.Pedido;
import br.com.fiap.kfcrazy.infra.adapters.repository.PedidoRepository;
import br.com.fiap.kfcrazy.domain.ports.PreparacaoServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PreparacaoService implements PreparacaoServicePort {

    private final PedidoRepository pedidoRepository;

    @Override
    public Pedido criarPedidoFakeCheckout(Pedido pedido) {
        pedido.setStatusPedido(StatusPedido.EM_PROCESSAMENTO);
        pedido.setStatusPagamento(StatusPagamento.PENDENTE);
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido enviarParaPreparacao(Long id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();
            if (pedido.getStatusPagamento() == StatusPagamento.APROVADO) {
                pedido.setStatusPedido(StatusPedido.EM_PREPARACAO);
                return pedidoRepository.save(pedido);
            }
        }
        return null;
    }

    @Override
    public Pedido iniciarPreparacao(Long id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();
            if (pedido.getStatusPedido() == StatusPedido.EM_PREPARACAO) {
                pedido.setStatusPedido(StatusPedido.PRONTO);
                return pedidoRepository.save(pedido);
            }
        }
        return null;
    }

    @Override
    public Pedido prepararPedido(Long id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();
            pedido.setStatusPedido(StatusPedido.PRONTO);
            return pedidoRepository.save(pedido);
        }
        return null;
    }

    @Override
    public Pedido cancelarPedido(Long id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();
            pedido.setStatusPedido(StatusPedido.CANCELADO);
            return pedidoRepository.save(pedido);
        }
        return null;
    }

    @Override
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }
}