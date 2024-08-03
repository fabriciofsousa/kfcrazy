package br.com.fiap.kfcrazy.pedido.infrastructure.repository;

import br.com.fiap.kfcrazy.pedido.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, String> {

    Optional<Pedido> findById(String id);

    void deleteById(String id);
}
