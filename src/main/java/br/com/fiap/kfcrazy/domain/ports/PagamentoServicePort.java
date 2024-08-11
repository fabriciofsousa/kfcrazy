package br.com.fiap.kfcrazy.domain.ports;

import br.com.fiap.kfcrazy.infra.adapters.entities.Pagamento;
import com.google.zxing.WriterException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Component
public interface PagamentoServicePort {

    Pagamento criarPagamento(String descricao, BigDecimal valor) throws WriterException, IOException;


    List<Pagamento> getAll();

    Optional<Pagamento> findById(Long id);

}