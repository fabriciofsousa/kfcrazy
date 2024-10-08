package br.com.fiap.kfcrazy.domain.ports;

import br.com.fiap.kfcrazy.domain.dto.request.PedidoRequestDTO;
import br.com.fiap.kfcrazy.infra.adapters.entities.Pedido;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface PedidoServicePort {


    Pedido create(PedidoRequestDTO pedido);

    List<Pedido> getAll();

    Pedido findById(Long id);

    void cancelar(Long id);
}