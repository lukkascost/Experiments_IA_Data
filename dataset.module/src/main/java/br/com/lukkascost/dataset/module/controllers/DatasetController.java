package br.com.lukkascost.dataset.module.controllers;

import br.com.lukkascost.dataset.module.services.IDatasetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/")
public class DatasetController {

    private final IDatasetService datasetService;

    public DatasetController(IDatasetService datasetService) {
        this.datasetService = datasetService;
    }

    @GetMapping
    public Object getDataset(@RequestParam(required = false) UUID dataset_id) {
        if(dataset_id == null){
            return new ResponseEntity(datasetService.findAll(), HttpStatus.OK);
        }
        return getDatasetById(dataset_id);
    }

    public Object getDatasetById(UUID dataset_id) {

        return new ResponseEntity( datasetService.findByID(dataset_id), HttpStatus.OK);
    }
}
