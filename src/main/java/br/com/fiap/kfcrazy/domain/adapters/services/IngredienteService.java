package br.com.fiap.kfcrazy.domain.adapters.services;

import br.com.fiap.kfcrazy.domain.enums.CategoriaProduto;
import br.com.fiap.kfcrazy.domain.ports.IngredienteServicePort;
import br.com.fiap.kfcrazy.domain.ports.ProdutoServicePort;
import br.com.fiap.kfcrazy.infra.adapters.entities.Ingrediente;
import br.com.fiap.kfcrazy.infra.adapters.entities.Produto;
import br.com.fiap.kfcrazy.infra.adapters.repository.IngredienteRepository;
import br.com.fiap.kfcrazy.infra.adapters.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class IngredienteService implements IngredienteServicePort {


    private final IngredienteRepository ingredienteRepository;

    @Override
    public Optional<Ingrediente> findById(Long id) {
        return ingredienteRepository.findById(id);
    }
}