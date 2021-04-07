package br.com.lukkascost.executions.module.controllers;


import br.com.lukkascost.commons.module.repositories.IDatasetRepository;
import br.com.lukkascost.commons.module.repositories.IRoundRepository;
import br.com.lukkascost.commons.module.repositories.ISampleRepository;
import br.com.lukkascost.executions.module.repositories.RoundRepository;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ExecutionController {
    private final IRoundRepository roundRepository;
    private final IDatasetRepository datasetRepository;
    private final ISampleRepository sampleRepository;

    public ExecutionController(RoundRepository roundRepository, IDatasetRepository datasetRepository, ISampleRepository sampleRepository) {
        this.roundRepository = roundRepository;
        this.datasetRepository = datasetRepository;
        this.sampleRepository = sampleRepository;
    }
}
