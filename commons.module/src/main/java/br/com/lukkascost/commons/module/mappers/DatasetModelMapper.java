package br.com.lukkascost.commons.module.mappers;

import br.com.lukkascost.commons.module.models.dto.DatasetDTO;
import br.com.lukkascost.commons.module.models.entities.DatasetEntity;
import br.com.lukkascost.commons.module.models.entities.SampleEntity;

import java.util.List;
import java.util.Set;

public abstract class DatasetModelMapper {

    public abstract DatasetDTO convert(DatasetEntity datasetEntity);

    public abstract List<DatasetDTO> convert(List<DatasetEntity> datasetEntities);

    public float map(Set<SampleEntity> past) {
        return past.size();
    }
}
