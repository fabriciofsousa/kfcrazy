package br.com.fiap.kfcrazy.Produto.infraestructure.service;

import br.com.fiap.kfcrazy.Produto.domain.Enum.CategoriaProduto;
import br.com.fiap.kfcrazy.Produto.domain.model.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {
    Produto create(Produto produto);

    Optional<Produto> findById(Long id);

    List<Produto> findAll();

    Produto update(Long id, Produto produto);

    Produto getByCategoria(CategoriaProduto categoriaProduto);

    void delete(Long id);
}
