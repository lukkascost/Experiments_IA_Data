package br.com.lukkascost.executions.module.mappers;

import br.com.lukkascost.commons.module.mappers.ExecutionModelMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {
                DatasetMapper.class,
                ExperimentMapper.class,
                DatasetDetailsMapper.class,
                SampleMapper.class,
                RoundMapper.class}
)
public abstract class ExecutionMapper extends ExecutionModelMapper {

}
