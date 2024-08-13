package br.com.fiap.kfcrazy.domain.ports;

import br.com.fiap.kfcrazy.domain.dto.request.ProdutoDTO;
import br.com.fiap.kfcrazy.domain.enums.CategoriaProduto;
import br.com.fiap.kfcrazy.infra.adapters.entities.Produto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public interface ProdutoServicePort {
    Produto create(Produto produto);

    Optional<Produto> findById(Long id);

    List<Produto> findAll();

    Produto update(Long id, ProdutoDTO produto);

    List<Produto> getByCategoria(CategoriaProduto categoriaProduto);

    void delete(Long id);
}
