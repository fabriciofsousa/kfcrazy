package br.com.fiap.kfcrazy.domain.adapters.services;

import br.com.fiap.kfcrazy.infra.adapters.entities.Pagamento;
import br.com.fiap.kfcrazy.infra.adapters.repository.PagamentoRepository;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class PagamentoService implements br.com.fiap.kfcrazy.domain.ports.PagamentoServicePort {

    private final PagamentoRepository pagamentoRepository;

    @Override
    public Pagamento criarPagamento(String descricao, BigDecimal valor) throws WriterException, IOException {
        Pagamento pagamento = new Pagamento();
        pagamento.setDescricao(descricao);
        pagamento.setValor(valor);

        // Gerar QR Code fixo com o conteúdo "mercadoPago"
        //String qrCode = mercadoLivre.generateQRCodeImage("mercadoPago", 300, 300);

        //pagamento.setQrCode(qrCode);
        return pagamentoRepository.save(pagamento);
    }


    @Override
    public List<Pagamento> getAll() {
        return pagamentoRepository.findAll();
    }

    @Override
    public Optional<Pagamento> findById(Long id) {
        return pagamentoRepository.findById(id);
    }

}
