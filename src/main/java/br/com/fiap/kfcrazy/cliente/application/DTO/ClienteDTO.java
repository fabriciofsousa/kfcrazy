package br.com.fiap.kfcrazy.cliente.application.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteDTO {
    private String cpf;
    private String nome;
    private String email;
}
