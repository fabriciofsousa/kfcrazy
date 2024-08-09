package br.com.fiap.kfcrazy.pedido.application.mapper;

import br.com.fiap.kfcrazy.Produto.application.dto.ProdutoDTO;
import br.com.fiap.kfcrazy.Produto.domain.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper
public interface ProdutoMapper {

    ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    Produto toEntity(ProdutoDTO produtoDTO);

    ProdutoDTO toDto(Produto produto);
}