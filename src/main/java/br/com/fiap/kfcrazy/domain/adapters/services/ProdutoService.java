package br.com.fiap.kfcrazy.domain.adapters.services;

import br.com.fiap.kfcrazy.domain.dto.request.IngredienteDTO;
import br.com.fiap.kfcrazy.domain.dto.request.ProdutoDTO;
import br.com.fiap.kfcrazy.domain.enums.CategoriaProduto;
import br.com.fiap.kfcrazy.domain.mapper.IngredienteMapper;
import br.com.fiap.kfcrazy.domain.mapper.ProdutoMapper;
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
    public Produto update(Long id, ProdutoDTO produtoDTO) {
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        // Atualiza os detalhes do produto
        produtoExistente.setCategoria(produtoDTO.getCategoria());
        produtoExistente.setNome(produtoDTO.getNome());
        produtoExistente.setDescricao(produtoDTO.getDescricao());
        produtoExistente.setPreco(produtoDTO.getPreco());

        // Atualiza ou adiciona os ingredientes
        for (IngredienteDTO ingredienteDTO : produtoDTO.getIngredientes()) {
            Ingrediente ingredienteExistente = produtoExistente.getIngredientes().stream()
                    .filter(ing -> ing.getId().equals(ingredienteDTO.getId()))
                    .findFirst()
                    .orElse(null);

            if (ingredienteExistente != null) {
                // Atualiza o ingrediente existente
                ingredienteExistente.setNome(ingredienteDTO.getNome());
            } else {
                // Adiciona um novo ingrediente
                Ingrediente novoIngrediente = IngredienteMapper.INSTANCE.toEntity(ingredienteDTO);
                novoIngrediente.setProduto(produtoExistente);
                produtoExistente.getIngredientes().add(novoIngrediente);
            }
        }

        return produtoRepository.save(produtoExistente);
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