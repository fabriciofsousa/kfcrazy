package br.com.fiap.kfcrazy.domain.adapters.services;

import br.com.fiap.kfcrazy.domain.dto.request.IngredienteRequestDTO;
import br.com.fiap.kfcrazy.domain.dto.request.PedidoRequestDTO;
import br.com.fiap.kfcrazy.domain.dto.request.ProdutoRequestDTO;
import br.com.fiap.kfcrazy.domain.ports.ClienteServicePort;
import br.com.fiap.kfcrazy.domain.ports.IngredienteServicePort;
import br.com.fiap.kfcrazy.domain.ports.ProdutoServicePort;
import br.com.fiap.kfcrazy.infra.adapters.entities.Cliente;
import br.com.fiap.kfcrazy.infra.adapters.entities.Ingrediente;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class PedidoService implements PedidoServicePort {

    private final PedidoRepository pedidoRepository;
    private final ProdutoServicePort produtoService;
    private final ClienteServicePort clienteService;
    private final IngredienteServicePort ingredienteService;


    @Override
    public Pedido create(PedidoRequestDTO pedidoRequestDTO) {
        Cliente cliente = null;
        if (pedidoRequestDTO.getClienteId() != null){
            cliente = clienteService.findById(pedidoRequestDTO.getClienteId())
                    .orElseGet(null);
        }

        Set<Produto> produtos = new HashSet<>();
        for (ProdutoRequestDTO produtoDTO : pedidoRequestDTO.getProdutos()) {
            Produto produto = produtoService.findById(produtoDTO.getId())
                    .orElseThrow(() -> new PedidoNaoEncontradoException("Produto não encontrado"));

            for (IngredienteRequestDTO ingredienteDTO : produtoDTO.getIngredientes()) {
                Ingrediente ingrediente = ingredienteService.findById(ingredienteDTO.getId())
                        .orElseThrow(() -> new PedidoNaoEncontradoException("Ingrediente não encontrado"));
                ingrediente.setQuantidade(String.valueOf(ingredienteDTO.getQuantidade()));
            }
            produtos.add(produto);
        }

        Pedido pedido = new Pedido();

        pedido.setCliente(cliente);
        pedido.setProdutos(produtos);

        pedido.setDataTransacao(LocalDateTime.now());

        pedido.setStatusPagamento(StatusPagamento.PENDENTE);
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
    public void cancelar(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado!"));
        pedido.setStatusPedido(StatusPedido.CANCELADO);
        pedido.setStatusPagamento(StatusPagamento.CANCELADO);
        pedidoRepository.save(pedido);
    }
}