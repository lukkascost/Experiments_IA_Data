package br.com.lukkascost.rounds.module.services.impl;

import br.com.lukkascost.commons.module.repositories.IDatasetRepository;
import br.com.lukkascost.commons.module.repositories.IExperimentRepository;
import br.com.lukkascost.commons.module.repositories.IRoundRepository;
import br.com.lukkascost.rounds.module.services.IRoundService;
import org.springframework.stereotype.Service;

@Service
public class IRoundServiceImpl implements IRoundService {

    private final IRoundRepository roundRepository;
    private final IDatasetRepository datasetRepository;
    private final IExperimentRepository experimentRepository;

    public IRoundServiceImpl(IRoundRepository roundRepository, IDatasetRepository datasetRepository, IExperimentRepository experimentRepository) {
        this.roundRepository = roundRepository;
        this.datasetRepository = datasetRepository;
        this.experimentRepository = experimentRepository;
    }
}
