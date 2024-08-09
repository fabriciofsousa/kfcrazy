package br.com.fiap.kfcrazy.pagamento.domain.model;

import br.com.fiap.kfcrazy.pagamento.domain.Enum.StatusPagamento;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity @Getter @Setter
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valor;
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;
    private String descricao;
    private String qrCode;
}