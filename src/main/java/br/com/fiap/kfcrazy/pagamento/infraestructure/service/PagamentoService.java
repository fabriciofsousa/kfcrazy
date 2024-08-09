package br.com.fiap.kfcrazy.pagamento.infraestructure.service;

import br.com.fiap.kfcrazy.pagamento.domain.model.Pagamento;
import com.google.zxing.WriterException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public interface PagamentoService {

    Pagamento criarPagamento(String descricao, BigDecimal valor) throws WriterException, IOException;

    String gerarQRCode(String qrCodeContent) throws WriterException, IOException;

    List<Pagamento> getAll();

    Optional<Pagamento> findById(Long id);

}