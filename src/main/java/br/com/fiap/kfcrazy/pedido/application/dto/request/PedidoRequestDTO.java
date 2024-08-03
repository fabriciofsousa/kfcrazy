package br.com.fiap.kfcrazy.pedido.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequestDTO {

    private String idCarrinho;
    private double valorTotal;
    private String numeroCartao;
    private String tipo;
    private String dataValidadeCartao;
    private String codigoSeguranca;
    private String codigoBoleto;
    private String formaEntrega;
}
