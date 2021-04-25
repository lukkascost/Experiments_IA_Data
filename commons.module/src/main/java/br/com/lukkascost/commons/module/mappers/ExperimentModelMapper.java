package br.com.lukkascost.commons.module.mappers;

import br.com.lukkascost.commons.module.models.dto.ExperimentsCreateDTO;
import br.com.lukkascost.commons.module.models.dto.ExperimentsDTO;
import br.com.lukkascost.commons.module.models.entities.ExperimentEntity;
import br.com.lukkascost.commons.module.models.entities.RoundEntity;
import br.com.lukkascost.commons.module.specifications.ExperimentsSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Set;

public abstract class ExperimentModelMapper {

    public abstract ExperimentsDTO convert(ExperimentEntity ExperimentEntity);

    public abstract List<ExperimentsDTO> convert(List<ExperimentEntity> datasetEntities);


    public abstract ExperimentsDTO convert(ExperimentsCreateDTO dto);

    public abstract ExperimentEntity convertEntity(ExperimentsCreateDTO dto);

    public float map(Set<RoundEntity> past) {
        return past.size();
    }

    public Specification<ExperimentEntity> convert(ExperimentsDTO experimentsDTO){
        Specification<ExperimentEntity> spec = Specification.where(ExperimentsSpecifications.withId(experimentsDTO.getId()));
        spec = spec.and(ExperimentsSpecifications.withName(experimentsDTO.getName()));
        return spec;
    }

    public PageImpl<ExperimentsDTO> convert(Page<ExperimentEntity> page){
        return new PageImpl(this.convert(page.getContent()),page.getPageable(),page.getTotalElements());
    }
}
