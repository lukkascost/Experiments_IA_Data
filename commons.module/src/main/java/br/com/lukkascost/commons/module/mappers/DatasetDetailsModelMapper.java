package br.com.lukkascost.commons.module.mappers;

import br.com.lukkascost.commons.module.models.dto.DatasetDetailsDTO;
import br.com.lukkascost.commons.module.models.entities.AttributeEntity;
import br.com.lukkascost.commons.module.models.entities.DatasetEntity;
import java.util.List;
import java.util.Set;

public abstract class DatasetDetailsModelMapper {

    public abstract List<DatasetDetailsDTO> convert(List<DatasetEntity> datasetEntities);

    public abstract DatasetDetailsDTO convert(DatasetEntity datasetEntity) ;

    public long map(Set<AttributeEntity> past) {
        return past.size();
    }

}
