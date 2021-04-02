package br.com.lukkascost.commons.module.mappers;

import br.com.lukkascost.commons.module.models.dto.SampleDTO;
import br.com.lukkascost.commons.module.models.entities.AttributeEntity;
import br.com.lukkascost.commons.module.models.entities.SampleEntity;
import br.com.lukkascost.commons.module.specifications.SampleSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public abstract class SampleModelMapper {

    public abstract SampleDTO convert(SampleEntity sampleEntity);

    public abstract List<SampleDTO> convert(List<SampleEntity> sampleEntities);

    public long map(Set<AttributeEntity> past) {
        return past.size();
    }

    public Specification<SampleEntity> convert(SampleDTO sampleDTO, UUID dataset_id){
        return Specification
                .where(SampleSpecifications.withDatasetId(dataset_id))
                .and(SampleSpecifications.withId(sampleDTO.getId()))
                .and(SampleSpecifications.withExtractorType(sampleDTO.getExtractorType()))
                ;
    }

    public PageImpl<SampleDTO> convert(Page<SampleEntity> page){
        return new PageImpl(this.convert(page.getContent()),page.getPageable(),page.getTotalElements());
    }
}
