package br.com.fiap.kfcrazy.Produto.infraestructure.service.impl;

import br.com.fiap.kfcrazy.Produto.domain.Enum.CategoriaProduto;
import br.com.fiap.kfcrazy.Produto.domain.model.Produto;
import br.com.fiap.kfcrazy.Produto.infraestructure.repository.ProdutoRepository;
import br.com.fiap.kfcrazy.Produto.infraestructure.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Produto create(Produto produto) {
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
    public Produto getByCategoria(CategoriaProduto categoriaProduto) {
        if (!produtoRepository.existsByCategoria(categoriaProduto)) {
            throw new RuntimeException("Produto não encontrado para a categoria especificada: "+categoriaProduto);
        }
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