package br.com.fiap.kfcrazy.domain.dto.request;

import lombok.*;

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
