package br.com.fiap.kfcrazy.domain.mapper;

import br.com.fiap.kfcrazy.domain.dto.request.ProdutoDTO;
import br.com.fiap.kfcrazy.domain.dto.response.IngredienteResponseDTO;
import br.com.fiap.kfcrazy.domain.dto.response.ProdutoResponseDTO;
import br.com.fiap.kfcrazy.infra.adapters.entities.Produto;
import br.com.fiap.kfcrazy.infra.adapters.entities.Ingrediente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProdutoMapper {

    ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    @Mapping(source = "ingredientes", target = "ingredientes")
    Produto toEntity(ProdutoDTO produtoDTO);

    ProdutoResponseDTO toDto(Produto produto);

    IngredienteResponseDTO toIngredienteResponseDTO(Ingrediente ingrediente);

    List<IngredienteResponseDTO> toIngredienteResponseDTOList(List<Ingrediente> ingredientes);

}
