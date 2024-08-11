package br.com.fiap.kfcrazy.infra.adapters.repository;

import br.com.fiap.kfcrazy.domain.enums.CategoriaProduto;
import br.com.fiap.kfcrazy.infra.adapters.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    boolean existsByCategoria(CategoriaProduto categoriaProduto);

    List<Produto> findByCategoria(CategoriaProduto categoriaProduto);

    }
