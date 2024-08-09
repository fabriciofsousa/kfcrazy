package br.com.fiap.kfcrazy.Produto.infraestructure.repository;

import br.com.fiap.kfcrazy.Produto.domain.Enum.CategoriaProduto;
import br.com.fiap.kfcrazy.Produto.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    boolean existsByCategoria(CategoriaProduto categoriaProduto);

    Produto findByCategoria(CategoriaProduto categoriaProduto);

    }
