package br.com.fiap.kfcrazy.domain.adapters.services;

import br.com.fiap.kfcrazy.infra.adapters.entities.Cliente;
import br.com.fiap.kfcrazy.infra.adapters.repository.ClienteRepository;
import br.com.fiap.kfcrazy.domain.ports.ClienteServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ClienteService implements ClienteServicePort {

    private final ClienteRepository clienteRepository;

    @Override
    public Cliente criarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> findByCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }
}