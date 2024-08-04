package br.com.fiap.kfcrazy.pedido.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.fiap.kfcrazy.pedido"})
@EnableJpaRepositories(basePackages = "br.com.fiap.kfcrazy.pedido.infrastructure.repository")
@EntityScan("br.com.fiap.kfcrazy.pedido.domain.model")
public class PedidoApplication {
    public static void main(String[] args) {
        SpringApplication.run(PedidoApplication.class, args);
    }
}
