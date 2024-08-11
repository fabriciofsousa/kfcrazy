package br.com.fiap.kfcrazy.infra.adapters.repository;

import br.com.fiap.kfcrazy.infra.adapters.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCpf(String cpf);
}
