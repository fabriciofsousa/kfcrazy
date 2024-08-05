package br.com.fiap.kfcrazy.preparacao.infraestructure.repository;

import br.com.fiap.kfcrazy.pagamento.domain.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
