package br.com.fiap.kfcrazy.Produto.infraestructure.service;

import br.com.fiap.kfcrazy.cliente.domain.model.Cliente;
import br.com.fiap.kfcrazy.pedido.domain.model.Pedido;

import java.util.List;

public interface ClienteService {
    Cliente criarCliente(Cliente cliente);
    Cliente atualizarCliente(Long id, Cliente cliente);
    void deletarCliente(Long id);
    List<Cliente> listarClientes();
}
