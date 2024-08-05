package br.com.fiap.kfcrazy.pedido.domain.Enum;

public enum TipoDePagamento {
    QR_CODE("QR Code");

    private String descricao;

    TipoDePagamento(String descricao) {
        this.descricao = descricao;
    }


    public String getDescricao() {
        return descricao;
    }
}