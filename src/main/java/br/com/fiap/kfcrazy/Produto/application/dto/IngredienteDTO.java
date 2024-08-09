package br.com.fiap.kfcrazy.Produto.application.dto;

import br.com.fiap.kfcrazy.Produto.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class IngredienteDTO {

    private String nome;

    private int quantidade;

}