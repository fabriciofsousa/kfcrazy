package br.com.fiap.kfcrazy.domain.adapters.services;

import br.com.fiap.kfcrazy.domain.enums.CategoriaProduto;
import br.com.fiap.kfcrazy.infra.adapters.entities.Ingrediente;
import br.com.fiap.kfcrazy.infra.adapters.entities.Produto;
import br.com.fiap.kfcrazy.infra.adapters.repository.ProdutoRepository;
import br.com.fiap.kfcrazy.domain.ports.ProdutoServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProdutoService implements ProdutoServicePort {


    private final ProdutoRepository produtoRepository;

    @Override
    public Produto create(Produto produto) {
        for (Ingrediente ingrediente : produto.getIngredientes()) {
            ingrediente.setProduto(produto);
        }
        return produtoRepository.save(produto);
    }

    @Override
    public Optional<Produto> findById(Long id) {
        return produtoRepository.findById(id);
    }

    @Override
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    @Override
    public Produto update(Long id, Produto produto) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado");
        }
        produto.setId(id);
        return produtoRepository.save(produto);
    }

    @Override
    public List<Produto> getByCategoria(CategoriaProduto categoriaProduto) {
        return produtoRepository.findByCategoria(categoriaProduto);
    }

    @Override
    public void delete(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado");
        }
        produtoRepository.deleteById(id);
    }
}