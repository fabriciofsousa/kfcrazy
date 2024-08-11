package br.com.fiap.kfcrazy.infra.adapters.repository;

import br.com.fiap.kfcrazy.infra.adapters.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
