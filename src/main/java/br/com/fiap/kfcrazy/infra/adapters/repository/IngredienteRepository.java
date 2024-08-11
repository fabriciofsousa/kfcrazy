package br.com.fiap.kfcrazy.infra.adapters.repository;

import br.com.fiap.kfcrazy.infra.adapters.entities.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
}
