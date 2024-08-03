package br.com.fiap.kfcrazy.pedido.domain.Enum;

public enum StatusPedido {
    AGUARDANDO_PAGAMENTO("Aguardando Pagamento"),
    EM_PROCESSAMENTO("Em Processamento"),
    ENVIADO("Enviado"),
    ENTREGUE("Entregue"),
    CANCELADO("Cancelado");

    private String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}