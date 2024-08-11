package br.com.fiap.kfcrazy.infra.adapters.repository;

import br.com.fiap.kfcrazy.infra.adapters.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, String> {

    Optional<Pedido> findById(Long id);

    void deleteById(Long id);
}
