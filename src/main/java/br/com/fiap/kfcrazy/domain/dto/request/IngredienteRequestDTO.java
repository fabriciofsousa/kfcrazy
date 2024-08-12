package br.com.fiap.kfcrazy.domain.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngredienteRequestDTO {

    @NotNull(message = "O ID do ingrediente é obrigatório.")
    private Long id;

    @Min(value = 0, message = "A quantidade do ingrediente deve ser no mínimo 0.")
    private int quantidade;
}
