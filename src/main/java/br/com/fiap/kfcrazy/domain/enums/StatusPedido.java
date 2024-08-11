package br.com.fiap.kfcrazy.domain.enums;

public enum StatusPedido {
    RECEBIDO("Recebido"),
    EM_PREPARACAO("Em Preparação"),
    PRONTO("Pronto"),
    FINALIZADO("Finalizado"),

    CANCELADO("Cancelado"),

    EM_PROCESSAMENTO("Em processamento");

    private String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}