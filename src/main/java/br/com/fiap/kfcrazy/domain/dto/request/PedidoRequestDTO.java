package br.com.fiap.kfcrazy.domain.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoRequestDTO {

    private Long clienteId;

    @NotNull(message = "A lista de produtos é obrigatória.")
    private List<ProdutoRequestDTO> produtos;
}
