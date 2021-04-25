package br.com.lukkascost.dataset.module.mappers;

import br.com.lukkascost.commons.module.mappers.DatasetModelMapper;
import br.com.lukkascost.commons.module.models.dto.DatasetCreateDTO;
import br.com.lukkascost.commons.module.models.entities.DatasetEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class DatasetMapper extends DatasetModelMapper {
}
