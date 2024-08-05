package br.com.fiap.kfcrazy.Produto.infraestructure.repository;

import br.com.fiap.kfcrazy.cliente.domain.model.Cliente;
import br.com.fiap.kfcrazy.pagamento.domain.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
