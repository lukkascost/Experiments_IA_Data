package br.com.lukkascost.commons.module.mappers;

import br.com.lukkascost.commons.module.models.dto.DatasetCreateDTO;
import br.com.lukkascost.commons.module.models.dto.DatasetDTO;
import br.com.lukkascost.commons.module.models.entities.DatasetEntity;
import br.com.lukkascost.commons.module.models.entities.SampleEntity;
import br.com.lukkascost.commons.module.specifications.DatasetSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Set;

public abstract class DatasetModelMapper {

    public abstract DatasetDTO convert(DatasetEntity datasetEntity);

    public abstract List<DatasetDTO> convert(List<DatasetEntity> datasetEntities);


    public abstract DatasetDTO convert(DatasetCreateDTO dto);

    public abstract DatasetEntity convertEntity(DatasetCreateDTO dto);


    public float map(Set<SampleEntity> past) {
        return past.size();
    }


    public Specification<DatasetEntity> convert(DatasetDTO datasetDTO){
        return Specification.where(DatasetSpecifications.withId(datasetDTO.getId()))
                .and(DatasetSpecifications.withName(datasetDTO.getName()));
    }
    public PageImpl<DatasetDTO> convert(Page<DatasetEntity> page){
        return new PageImpl(this.convert(page.getContent()),page.getPageable(),page.getTotalElements());
    }
}
