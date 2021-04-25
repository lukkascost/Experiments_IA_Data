package br.com.lukkascost.attributes.module.services;

import br.com.lukkascost.commons.module.models.dto.AttributeCreateDTO;
import br.com.lukkascost.commons.module.models.dto.AttributeDetailsDTO;
import br.com.lukkascost.commons.module.models.dto.AttributeValueDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IAttributeService {

    AttributeDetailsDTO create(AttributeCreateDTO dto);

    Page<AttributeDetailsDTO> findAll(AttributeDetailsDTO executionDetailsDTO, UUID sample_id, Pageable pageable);

    AttributeValueDTO findAllValues(AttributeDetailsDTO attributeDetailsDTO, UUID sample_id, Pageable pageable);
}
