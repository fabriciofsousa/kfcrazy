package br.com.fiap.kfcrazy.domain.ports;

import br.com.fiap.kfcrazy.infra.adapters.entities.Ingrediente;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface IngredienteServicePort {
    Optional<Ingrediente> findById(Long id);

}
