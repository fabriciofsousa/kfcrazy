package br.com.fiap.kfcrazy.pedido.infrastructure.service;

import br.com.fiap.kfcrazy.pedido.application.dto.request.PedidoRequestDTO;
import br.com.fiap.kfcrazy.pedido.application.dto.response.ResponseDTO;
import br.com.fiap.kfcrazy.pedido.domain.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoService {


    public Optional<ResponseDTO> finalizarPedido(PedidoRequestDTO pedido);

    public Optional<List<Pedido>> getAll() throws Exception;

    public Optional<ResponseDTO> findById(String id);

    public Pedido update(String id, PedidoRequestDTO pedido) throws Exception;

    public void delete(String id);
}
