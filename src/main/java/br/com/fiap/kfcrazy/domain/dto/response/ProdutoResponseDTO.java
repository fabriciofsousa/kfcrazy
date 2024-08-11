package br.com.fiap.kfcrazy.domain.dto.response;

import br.com.fiap.kfcrazy.domain.enums.CategoriaProduto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProdutoResponseDTO {

    private Long id;
    private CategoriaProduto categoria;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private List<IngredienteResponseDTO> ingredientes;
}
