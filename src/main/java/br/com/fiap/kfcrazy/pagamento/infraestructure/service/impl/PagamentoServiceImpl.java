package br.com.fiap.kfcrazy.pagamento.infraestructure.service.impl;

import br.com.fiap.kfcrazy.pagamento.domain.model.Pagamento;
import br.com.fiap.kfcrazy.pagamento.infraestructure.repository.PagamentoRepository;
import br.com.fiap.kfcrazy.pagamento.infraestructure.service.PagamentoService;
import br.com.fiap.kfcrazy.pagamento.infraestructure.service.QrCodeService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PagamentoServiceImpl implements PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final QrCodeService qrCodeService;

    @Override
    public Pagamento criarPagamento(String descricao, BigDecimal valor) throws WriterException, IOException {
        Pagamento pagamento = new Pagamento();
        pagamento.setDescricao(descricao);
        pagamento.setValor(valor);

        // Gerar QR Code fixo com o conteúdo "mercadoPago"
        String qrCode = qrCodeService.generateQRCodeImage("mercadoPago", 300, 300);

        pagamento.setQrCode(qrCode);
        return pagamentoRepository.save(pagamento);
    }

    @Override
    public String gerarQRCode(String qrCodeContent) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        int width = 300;
        int height = 300;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        var bitMatrix = qrCodeWriter.encode(qrCodeContent, BarcodeFormat.QR_CODE, width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", outputStream);
        byte[] qrCodeBytes = outputStream.toByteArray();

        return Base64.getEncoder().encodeToString(qrCodeBytes);
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
