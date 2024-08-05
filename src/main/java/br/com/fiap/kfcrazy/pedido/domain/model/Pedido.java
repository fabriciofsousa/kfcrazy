package br.com.fiap.kfcrazy.pedido.domain.model;

import br.com.fiap.kfcrazy.Produto.domain.model.Produto;
import br.com.fiap.kfcrazy.cliente.domain.model.Cliente;
import br.com.fiap.kfcrazy.pagamento.domain.model.Pagamento;
import br.com.fiap.kfcrazy.pedido.domain.Enum.StatusPagamento;
import br.com.fiap.kfcrazy.pedido.domain.Enum.StatusPedido;
import br.com.fiap.kfcrazy.pedido.domain.Enum.TipoDePagamento;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Produto> produtos;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @Column(nullable = false)
    private LocalDateTime dataTransacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pagamento", nullable = false)
    private StatusPagamento statusPagamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDePagamento tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pedido", nullable = false)
    private StatusPedido statusPedido;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pagamento_id", referencedColumnName = "id")
    private Pagamento pagamento;
}
