package br.com.fiap.kfcrazy.pedido.application.dto.request;

import br.com.fiap.kfcrazy.Produto.application.dto.ProdutoDTO;
import br.com.fiap.kfcrazy.Produto.domain.model.Produto;
import br.com.fiap.kfcrazy.cliente.application.DTO.ClienteDTO;
import br.com.fiap.kfcrazy.cliente.domain.model.Cliente;
import br.com.fiap.kfcrazy.pagamento.domain.Enum.StatusPagamento;
import br.com.fiap.kfcrazy.pagamento.domain.Enum.TipoDePagamento;
import br.com.fiap.kfcrazy.pagamento.domain.model.Pagamento;
import br.com.fiap.kfcrazy.pedido.domain.Enum.StatusPedido;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoRequestDTO {

    private ClienteDTO cliente;
    private Set<ProdutoDTO> produtos;

}
