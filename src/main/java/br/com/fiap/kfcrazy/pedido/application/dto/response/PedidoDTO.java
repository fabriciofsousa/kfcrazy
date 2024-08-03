package br.com.fiap.kfcrazy.pedido.application.dto.response;

import br.com.fiap.kfcrazy.pedido.domain.Enum.StatusPagamento;
import br.com.fiap.kfcrazy.pedido.domain.Enum.StatusPedido;
import br.com.fiap.kfcrazy.pedido.domain.Enum.TipoDePagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    private Long id;
    private String idCarrinho;
    private TipoDePagamento tipo;
    private Date dataTransacao;
    private StatusPagamento statusPagamento;
    private StatusPedido statusPedido;

}
