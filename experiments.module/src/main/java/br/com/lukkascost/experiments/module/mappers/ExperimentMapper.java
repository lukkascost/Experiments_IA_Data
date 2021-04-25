package br.com.lukkascost.experiments.module.mappers;

import br.com.lukkascost.commons.module.mappers.DatasetModelMapper;
import br.com.lukkascost.commons.module.mappers.ExperimentModelMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ExperimentMapper extends ExperimentModelMapper {
}
