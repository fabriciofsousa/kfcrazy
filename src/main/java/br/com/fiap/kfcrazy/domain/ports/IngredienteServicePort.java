package br.com.fiap.kfcrazy.domain.ports;

import br.com.fiap.kfcrazy.infra.adapters.entities.Ingrediente;

import java.util.Optional;

public interface IngredienteServicePort {
    Optional<Ingrediente> findById(Long id);

}
