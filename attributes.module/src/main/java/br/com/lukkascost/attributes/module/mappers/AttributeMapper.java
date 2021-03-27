package br.com.lukkascost.attributes.module.mappers;

import br.com.lukkascost.attributes.module.models.AttributeDetailsDTO;
import br.com.lukkascost.commons.module.models.entities.AttributeEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AttributeMapper {
    public abstract List<AttributeDetailsDTO> convert(List<AttributeEntity> attributeEntityList);

}
