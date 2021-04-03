package br.com.lukkascost.commons.module.mappers;

import br.com.lukkascost.commons.module.models.dto.AttributeCreateDTO;
import br.com.lukkascost.commons.module.models.dto.AttributeDetailsDTO;
import br.com.lukkascost.commons.module.models.entities.AttributeEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static br.com.lukkascost.commons.module.specifications.AttributeSpecifications.withName;
import static br.com.lukkascost.commons.module.specifications.AttributeSpecifications.withSampleId;

public abstract class AttributeModelMapper {
    public abstract List<AttributeDetailsDTO> convert(List<AttributeEntity> attributeEntityList);
    public abstract AttributeDetailsDTO convert(AttributeEntity entity);
    public abstract AttributeEntity convertEntity(AttributeCreateDTO dto);


    public Specification<AttributeEntity> convert(AttributeCreateDTO dto){
        return Specification
                .where(withSampleId(dto.getSampleId()))
                .and(withName(dto.getName()))
        ;
    }

}
