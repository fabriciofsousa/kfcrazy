package br.com.fiap.kfcrazy.pedido.application.exception;

public class PedidoNaoEncontradoException extends RuntimeException {

    public PedidoNaoEncontradoException(String message) {
        super(message);
    }
}

