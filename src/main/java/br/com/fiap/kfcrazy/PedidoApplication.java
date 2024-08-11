package br.com.fiap.kfcrazy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.fiap.kfcrazy")
@ComponentScan(basePackages = {
        "br.com.fiap.kfcrazy.application",
        "br.com.fiap.kfcrazy.domain",
        "br.com.fiap.kfcrazy.infra"

})
@EnableJpaRepositories(basePackages = {
        "br.com.fiap.kfcrazy.infra.adapters.repository"
})
@EntityScan(basePackages = {
        "br.com.fiap.kfcrazy.infra.adapters.entities"
})
public class PedidoApplication {
    public static void main(String[] args) {
        SpringApplication.run(PedidoApplication.class, args);
    }
}
