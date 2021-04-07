package br.com.lukkascost.commons.module.mappers;

import br.com.lukkascost.commons.module.models.dto.ExecutionCreateDTO;
import br.com.lukkascost.commons.module.models.dto.ExecutionDetailsDTO;
import br.com.lukkascost.commons.module.models.entities.ExecutionEntity;
import br.com.lukkascost.commons.module.specifications.ExecutionSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public abstract class ExecutionModelMapper {

    public abstract ExecutionDetailsDTO convert(ExecutionEntity ExecutionEntity);

    public abstract List<ExecutionDetailsDTO> convert(List<ExecutionEntity> entityList);


    public abstract ExecutionDetailsDTO convert(ExecutionCreateDTO dto);

    public abstract ExecutionEntity convertEntity(ExecutionCreateDTO dto);

    public Specification<ExecutionEntity> convert(ExecutionDetailsDTO dto){
        Specification<ExecutionEntity> spec = Specification.where(ExecutionSpecifications.withId(dto.getId()));
        if(dto.getRound() != null) spec = spec.and(ExecutionSpecifications.withRoundId(dto.getRound().getId()));
        return spec;
    }
    public PageImpl<ExecutionDetailsDTO> convert(Page<ExecutionEntity> page){
        return new PageImpl(this.convert(page.getContent()),page.getPageable(),page.getTotalElements());
    }
}
