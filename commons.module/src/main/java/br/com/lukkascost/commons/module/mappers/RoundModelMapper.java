package br.com.lukkascost.commons.module.mappers;

import br.com.lukkascost.commons.module.models.dto.RoundCreateDTO;
import br.com.lukkascost.commons.module.models.dto.RoundDTO;
import br.com.lukkascost.commons.module.models.entities.RoundEntity;
import br.com.lukkascost.commons.module.specifications.RoundSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public abstract class RoundModelMapper {

    public abstract RoundDTO convert(RoundEntity roundEntity);

    public abstract List<RoundDTO> convert(List<RoundEntity> entityList);


    public abstract RoundDTO convert(RoundCreateDTO dto);

    public abstract RoundEntity convertEntity(RoundCreateDTO dto);

    public Specification<RoundEntity> convert(RoundDTO dto){
        return Specification
                .where(RoundSpecifications.withId(dto.getId()))
                .and(RoundSpecifications.withDatasetId(dto.getDatasetDTO().getId())
                .and(RoundSpecifications.withExperimentId(dto.getExperimentsDTO().getId()))
                );
    }
    public PageImpl<RoundDTO> convert(Page<RoundEntity> page){
        return new PageImpl(this.convert(page.getContent()),page.getPageable(),page.getTotalElements());
    }
}
