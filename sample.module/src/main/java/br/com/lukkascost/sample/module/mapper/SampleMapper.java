package br.com.lukkascost.sample.module.mapper;

import br.com.lukkascost.commons.module.mappers.SampleModelMapper;
import br.com.lukkascost.commons.module.models.dto.SampleDTO;
import br.com.lukkascost.commons.module.models.entities.SampleEntity;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class SampleMapper extends SampleModelMapper {

}
