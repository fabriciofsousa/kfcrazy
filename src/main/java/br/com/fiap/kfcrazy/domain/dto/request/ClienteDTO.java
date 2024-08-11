package br.com.fiap.kfcrazy.domain.dto.request;

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
