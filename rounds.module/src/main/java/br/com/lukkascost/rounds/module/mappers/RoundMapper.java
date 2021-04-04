package br.com.lukkascost.rounds.module.mappers;

import br.com.lukkascost.commons.module.mappers.ExperimentModelMapper;
import br.com.lukkascost.commons.module.mappers.RoundModelMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class RoundMapper extends RoundModelMapper {
}
