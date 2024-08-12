package br.com.fiap.kfcrazy.domain.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequestDTO {

    @NotNull(message = "O ID do produto é obrigatório.")
    private Long id;

    @NotNull(message = "A lista de ingredientes é obrigatória.")
    private List<IngredienteRequestDTO> ingredientes;
}
