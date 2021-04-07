package br.com.lukkascost.commons.module.mappers;

import br.com.lukkascost.commons.module.models.dto.AttributeCreateDTO;
import br.com.lukkascost.commons.module.models.dto.AttributeDetailsDTO;
import br.com.lukkascost.commons.module.models.dto.AttributeValueDTO;
import br.com.lukkascost.commons.module.models.entities.AttributeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

import static br.com.lukkascost.commons.module.specifications.AttributeSpecifications.*;

public abstract class AttributeModelMapper {


    public abstract List<AttributeDetailsDTO> convert(List<AttributeEntity> attributeEntityList);
    public abstract AttributeDetailsDTO convert(AttributeEntity entity);
    public abstract AttributeEntity convertEntity(AttributeCreateDTO dto);

    public AttributeValueDTO convertValue(List<AttributeEntity> attributeEntityList){
        AttributeValueDTO valueDTO = new AttributeValueDTO();
        valueDTO.setValueOrdered(new String[attributeEntityList.size()]);
        for (AttributeEntity att : attributeEntityList) {
            valueDTO.setSampleId(att.getSample().getId());
            valueDTO.getValueOrdered()[att.getOrder()] = att.getValue();
            valueDTO.setLabel(att.getSample().getLabel());
        }
        return valueDTO;
    }


    public PageImpl<AttributeDetailsDTO> convert(Page<AttributeEntity> page){
        return new PageImpl(this.convert(page.getContent()),page.getPageable(),page.getTotalElements());
    }
    public Specification<AttributeEntity> convert(AttributeDetailsDTO dto, UUID sampleId){
        Specification<AttributeEntity> spec = Specification.where(withId(dto.getId()))
                .and(withName(dto.getName()))
                .and(withSampleId(sampleId));
        return spec;
    }

    public Specification<AttributeEntity> convert(AttributeCreateDTO dto){
        return Specification
                .where(withSampleId(dto.getSampleId()))
                .and(withName(dto.getName()))
        ;
    }

}
