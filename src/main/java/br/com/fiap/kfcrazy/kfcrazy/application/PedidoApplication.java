package br.com.fiap.kfcrazy.kfcrazy.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.fiap.kfcrazy")
@ComponentScan(basePackages = {
        "br.com.fiap.kfcrazy.pedido",
        "br.com.fiap.kfcrazy.cliente",
        "br.com.fiap.kfcrazy.produto",
        "br.com.fiap.kfcrazy.preparacao",
        "br.com.fiap.kfcrazy.pagamento"
})
@EnableJpaRepositories(basePackages = {
        "br.com.fiap.kfcrazy.pedido.infrastructure.repository",
        "br.com.fiap.kfcrazy.cliente.infraestructure.repository",
        "br.com.fiap.kfcrazy.pagamento.infraestructure.repository",
        "br.com.fiap.kfcrazy.produto.infraestructure.repository"
})
@EntityScan(basePackages = {
        "br.com.fiap.kfcrazy.pedido.domain.model",
        "br.com.fiap.kfcrazy.cliente.domain.model",
        "br.com.fiap.kfcrazy.pagamento.domain.model",
        "br.com.fiap.kfcrazy.produto.domain.model"
})
public class PedidoApplication {
    public static void main(String[] args) {
        SpringApplication.run(PedidoApplication.class, args);
    }
}
