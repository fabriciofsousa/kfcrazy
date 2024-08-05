package br.com.fiap.kfcrazy.pagamento.infraestructure.service;

import com.google.zxing.WriterException;

import java.io.IOException;

public abstract class QrCodeService {

    public abstract String generateQRCodeImage(String text, int width, int height) throws WriterException, IOException;
}
