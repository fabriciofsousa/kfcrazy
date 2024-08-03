package br.com.fiap.kfcrazy.pedido.domain.Enum;

public enum StatusPagamento {
    PENDENTE("Pendente"),
    PROCESSANDO("Processando"),
    APROVADO("Aprovado"),
    REJEITADO("Rejeitado"),
    ESTORNADO("Estornado"),
    CANCELADO("Cancelado");

    private String descricao;

    StatusPagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}