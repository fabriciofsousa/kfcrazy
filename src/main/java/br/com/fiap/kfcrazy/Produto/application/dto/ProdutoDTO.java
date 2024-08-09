package br.com.fiap.kfcrazy.Produto.application.dto;

import br.com.fiap.kfcrazy.Produto.domain.Enum.CategoriaProduto;
import br.com.fiap.kfcrazy.Produto.domain.model.Ingrediente;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
