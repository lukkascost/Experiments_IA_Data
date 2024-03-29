package br.com.lukkascost.commons.module.mappers;

import br.com.lukkascost.commons.module.models.dto.RoundCreateDTO;
import br.com.lukkascost.commons.module.models.dto.RoundDTO;
import br.com.lukkascost.commons.module.models.dto.RoundDetailsDTO;
import br.com.lukkascost.commons.module.models.entities.ExecutionEntity;
import br.com.lukkascost.commons.module.models.entities.RoundEntity;
import br.com.lukkascost.commons.module.models.entities.SampleEntity;
import br.com.lukkascost.commons.module.specifications.RoundSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Set;

public abstract class RoundModelMapper {

    public abstract RoundDTO convert(RoundEntity roundEntity);

    public abstract List<RoundDTO> convert(List<RoundEntity> entityList);
    public abstract List<RoundDetailsDTO> convertDetails(List<RoundEntity> entityList);
    public long map(Set<ExecutionEntity> past) {
        return past.size();
    }


    public abstract RoundDTO convert(RoundCreateDTO dto);

    public abstract RoundEntity convertEntity(RoundCreateDTO dto);

    public abstract RoundDetailsDTO convertDetails(RoundEntity entity);

    public Specification<RoundEntity> convert(RoundDTO dto){
        Specification<RoundEntity> spec = Specification.where(RoundSpecifications.withId(dto.getId()))
                .and(RoundSpecifications.withName(dto.getName()));

        if(dto.getDataset() != null) spec = spec.and(RoundSpecifications.withDatasetId(dto.getDataset().getId()));
        if(dto.getExperiment() != null) spec = spec.and(RoundSpecifications.withExperimentId(dto.getExperiment().getId()));
        return spec;
    }

    public Specification<RoundEntity> convert(RoundDetailsDTO dto){
        Specification<RoundEntity> spec = Specification.where(RoundSpecifications.withId(dto.getId()))
                .and(RoundSpecifications.withName(dto.getName()));
        if(dto.getDataset() != null) spec = spec.and(RoundSpecifications.withDatasetId(dto.getDataset().getId()));
        if(dto.getExperiment() != null) spec = spec.and(RoundSpecifications.withExperimentId(dto.getExperiment().getId()));
        return spec;
    }

    public PageImpl<RoundDetailsDTO> convertDetails(Page<RoundEntity> page){
        return new PageImpl(this.convertDetails(page.getContent()),page.getPageable(),page.getTotalElements());
    }

    public PageImpl<RoundDTO> convert(Page<RoundEntity> page){
        return new PageImpl(this.convert(page.getContent()),page.getPageable(),page.getTotalElements());
    }
}
