package br.com.fiap.kfcrazy.domain.mapper;

import br.com.fiap.kfcrazy.domain.dto.request.IngredienteDTO;
import br.com.fiap.kfcrazy.infra.adapters.entities.Ingrediente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IngredienteMapper {
    IngredienteMapper INSTANCE = Mappers.getMapper(IngredienteMapper.class);

    Ingrediente toEntity(IngredienteDTO ingredienteDTO);
    IngredienteDTO toDto(Ingrediente ingrediente);
}