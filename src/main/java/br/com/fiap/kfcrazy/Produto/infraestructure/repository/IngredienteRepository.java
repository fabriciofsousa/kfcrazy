package br.com.fiap.kfcrazy.Produto.infraestructure.repository;

import br.com.fiap.kfcrazy.Produto.domain.model.Ingrediente;
import br.com.fiap.kfcrazy.Produto.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
}
