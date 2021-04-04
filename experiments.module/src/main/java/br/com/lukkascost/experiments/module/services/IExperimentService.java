package br.com.lukkascost.experiments.module.services;


import br.com.lukkascost.commons.module.models.dto.DatasetCreateDTO;
import br.com.lukkascost.commons.module.models.dto.DatasetDTO;
import br.com.lukkascost.commons.module.models.dto.ExperimentsCreateDTO;
import br.com.lukkascost.commons.module.models.dto.ExperimentsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IExperimentService {
    Page<ExperimentsDTO> findAll(ExperimentsDTO dto, Pageable pageable);

    ExperimentsDTO create(ExperimentsCreateDTO dto);
}
