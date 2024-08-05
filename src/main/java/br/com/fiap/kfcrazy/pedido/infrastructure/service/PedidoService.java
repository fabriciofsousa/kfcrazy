package br.com.fiap.kfcrazy.pedido.infrastructure.service;

import br.com.fiap.kfcrazy.pedido.application.dto.request.PedidoRequestDTO;
import br.com.fiap.kfcrazy.pedido.application.dto.response.ResponseDTO;
import br.com.fiap.kfcrazy.pedido.domain.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoService {


    Pedido create(Pedido pedido);

    List<Pedido> getAll();

    Pedido findById(Long id);

    Pedido update(Long id, Pedido pedido);

    void delete(Long id);
}
