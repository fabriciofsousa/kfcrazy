package br.com.fiap.kfcrazy.pedido.infrastructure.service;

import br.com.fiap.kfcrazy.pedido.domain.model.Pedido;
import java.util.List;

public interface PedidoService {


    Pedido create(Pedido pedido);

    List<Pedido> getAll();

    Pedido findById(Long id);

    void delete(Long id);
}
