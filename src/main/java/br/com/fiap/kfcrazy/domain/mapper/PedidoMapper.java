package br.com.fiap.kfcrazy.domain.mapper;

import br.com.fiap.kfcrazy.domain.dto.request.PedidoRequestDTO;
import br.com.fiap.kfcrazy.domain.dto.request.ProdutoDTO;
import br.com.fiap.kfcrazy.domain.dto.response.IngredienteResponseDTO;
import br.com.fiap.kfcrazy.domain.dto.response.ProdutoResponseDTO;
import br.com.fiap.kfcrazy.infra.adapters.entities.Ingrediente;
import br.com.fiap.kfcrazy.infra.adapters.entities.Pedido;
import br.com.fiap.kfcrazy.infra.adapters.entities.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    Pedido toEntity(PedidoRequestDTO pedidoRequestDTO);

}
