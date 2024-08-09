package br.com.fiap.kfcrazy.preparacao.infraestructure.service.impl;

import br.com.fiap.kfcrazy.pagamento.domain.Enum.StatusPagamento;
import br.com.fiap.kfcrazy.pedido.domain.Enum.StatusPedido;
import br.com.fiap.kfcrazy.pedido.domain.model.Pedido;
import br.com.fiap.kfcrazy.pedido.infrastructure.repository.PedidoRepository;
import br.com.fiap.kfcrazy.preparacao.infraestructure.service.PreparacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PreparacaoServiceImpl extends PreparacaoService {

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
                // Aqui você pode adicionar lógica para iniciar a preparação do pedido
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