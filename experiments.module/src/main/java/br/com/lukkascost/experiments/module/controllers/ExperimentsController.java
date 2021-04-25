package br.com.lukkascost.experiments.module.controllers;


import br.com.lukkascost.commons.module.models.dto.DatasetCreateDTO;
import br.com.lukkascost.commons.module.models.dto.ExperimentsCreateDTO;
import br.com.lukkascost.commons.module.models.dto.ExperimentsDTO;
import br.com.lukkascost.experiments.module.services.IExperimentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/")
public class ExperimentsController {
    private final IExperimentService experimentService;

    public ExperimentsController(IExperimentService experimentService) {
        this.experimentService = experimentService;
    }


    @GetMapping
    public ResponseEntity<Page<ExperimentsDTO>> getDataset(ExperimentsDTO dto,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity(experimentService.findAll(dto, pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createDataset(@RequestBody ExperimentsCreateDTO dto){
        return new ResponseEntity(experimentService.create(dto),HttpStatus.CREATED);
    }
}
