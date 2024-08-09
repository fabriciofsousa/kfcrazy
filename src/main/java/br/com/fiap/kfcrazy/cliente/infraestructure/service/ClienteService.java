package br.com.fiap.kfcrazy.cliente.infraestructure.service;

import br.com.fiap.kfcrazy.cliente.domain.model.Cliente;
import br.com.fiap.kfcrazy.pedido.domain.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    Cliente criarCliente(Cliente cliente);
    List<Cliente> listarClientes();

    Optional<Cliente> findByCpf(String cpf);
}
