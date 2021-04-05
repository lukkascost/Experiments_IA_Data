package br.com.lukkascost.rounds.module.mappers;

import br.com.lukkascost.commons.module.mappers.ExperimentModelMapper;
import br.com.lukkascost.commons.module.mappers.RoundModelMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {
                DatasetMapper.class,
                ExperimentMapper.class,
                DatasetDetailsMapper.class,
                SampleMapper.class
})
public abstract class RoundMapper extends RoundModelMapper {
}
