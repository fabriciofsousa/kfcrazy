package br.com.fiap.kfcrazy.domain.dto.request;

import br.com.fiap.kfcrazy.domain.enums.CategoriaProduto;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProdutoDTO {

    private CategoriaProduto categoria;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private List<IngredienteDTO> ingredientes;
}
