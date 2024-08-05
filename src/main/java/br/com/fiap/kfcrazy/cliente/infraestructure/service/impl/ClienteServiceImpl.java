package br.com.fiap.kfcrazy.cliente.infraestructure.service.impl;

import br.com.fiap.kfcrazy.cliente.domain.model.Cliente;
import br.com.fiap.kfcrazy.cliente.infraestructure.repository.ClienteRepository;
import br.com.fiap.kfcrazy.cliente.infraestructure.service.ClienteService;
import br.com.fiap.kfcrazy.pedido.domain.Enum.StatusPagamento;
import br.com.fiap.kfcrazy.pedido.domain.Enum.StatusPedido;
import br.com.fiap.kfcrazy.pedido.domain.model.Pedido;
import br.com.fiap.kfcrazy.pedido.infrastructure.repository.PedidoRepository;
import br.com.fiap.kfcrazy.preparacao.infraestructure.service.PreparacaoService;
import ch.qos.logback.core.net.server.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public Cliente criarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente atualizarCliente(Long id, Cliente cliente) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente clienteExistente = clienteOptional.get();
            clienteExistente.setCpf(cliente.getCpf());
            clienteExistente.setNome(cliente.getNome());
            clienteExistente.setEmail(cliente.getEmail());
            return clienteRepository.save(clienteExistente);
        }
        return null;
    }

    @Override
    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }
}