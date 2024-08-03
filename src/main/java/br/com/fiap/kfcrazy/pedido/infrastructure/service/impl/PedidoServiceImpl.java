package br.com.fiap.kfcrazy.pedido.infrastructure.service.impl;

import br.com.fiap.kfcrazy.pedido.application.dto.request.PedidoRequestDTO;
import br.com.fiap.kfcrazy.pedido.application.dto.response.ResponseDTO;
import br.com.fiap.kfcrazy.pedido.application.exception.PedidoNaoEncontradoException;
import br.com.fiap.kfcrazy.pedido.domain.Enum.TipoDePagamento;
import br.com.fiap.kfcrazy.pedido.domain.converter.PedidoConverter;
import br.com.fiap.kfcrazy.pedido.domain.model.Pedido;
import br.com.fiap.kfcrazy.pedido.infrastructure.repository.PedidoRepository;
import br.com.fiap.kfcrazy.pedido.infrastructure.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    private PedidoConverter pedidoConverter = new PedidoConverter();

    @Override
    public Optional<ResponseDTO> finalizarPedido(PedidoRequestDTO pedido) {
        Optional<Pedido> pedidoRetorno = Optional.of(pedidoRepository.save(pedidoConverter.criarPedido(pedido)));
        return Optional.of(
                pedidoRetorno.map(
                        pedidoMap ->
                                new ResponseDTO("Pedido criado com sucesso!",
                                        pedidoConverter.convertToResponseDTO(pedidoMap), LocalDateTime.now())
                    ).orElse(new ResponseDTO("Erro ao criar o pedido", pedido, LocalDateTime.now())));
    }

    @Override
    public Optional<List<Pedido>> getAll() throws Exception {
        return Optional.ofNullable(Optional.ofNullable(pedidoRepository.findAll()).orElseThrow(() -> new Exception("Erro ao buscar lista de Pedidos!")));
    }

    @Override
    public Optional<ResponseDTO> findById(String id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return Optional.ofNullable(pedido.map(pedidoMap -> new ResponseDTO("pedido encontrado!", pedidoMap, LocalDateTime.now())).orElseThrow(() -> new PedidoNaoEncontradoException("Não encontrado pedido de ID " + id)));

    }

    @Override
    public Pedido update(String id, PedidoRequestDTO pedidoRequest) throws Exception {
        Pedido pedido = pedidoRepository.findById(String.valueOf(id)).orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado!"));

        pedido.setDataTransacao(new Date());
        pedido.setTipo(TipoDePagamento.valueOf(pedidoRequest.getTipo()));
        pedido.setCodigoBoleto(pedidoRequest.getCodigoBoleto());
        pedido.setCodigoSeguranca(pedidoRequest.getCodigoSeguranca());
        pedido.setValorTotal(pedidoRequest.getValorTotal());
        pedido.setNumeroCartao(pedidoRequest.getNumeroCartao());
        pedido.setDataValidadeCartao(pedidoRequest.getDataValidadeCartao());
        pedido.setFormaEntrega(pedidoRequest.getFormaEntrega());

        return pedidoRepository.save(pedido);

    }

    @Override
    public void delete(String id) {
        pedidoRepository.findById(String.valueOf(id)).orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado!"));
        this.pedidoRepository.deleteById(id);
    }

}
