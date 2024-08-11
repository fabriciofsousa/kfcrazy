package br.com.fiap.kfcrazy.domain.ports;

import br.com.fiap.kfcrazy.infra.adapters.entities.Cliente;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ClienteServicePort {
    Cliente criarCliente(Cliente cliente);
    List<Cliente> listarClientes();

    Optional<Cliente> findByCpf(String cpf);
}
