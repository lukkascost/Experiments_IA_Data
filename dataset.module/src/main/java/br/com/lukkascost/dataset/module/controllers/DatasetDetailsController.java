package br.com.lukkascost.dataset.module.controllers;

import br.com.lukkascost.dataset.module.services.IDatasetDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "details")
public class DatasetDetailsController {

    private final IDatasetDetailsService detailsService;

    public DatasetDetailsController(IDatasetDetailsService detailsService) {
        this.detailsService = detailsService;
    }


    @GetMapping
    public Object getDatasetDetails(@RequestParam(required = false) UUID dataset_id) {
        if(dataset_id == null){
            return new ResponseEntity(detailsService.findAll(), HttpStatus.OK);
        }
        return getDatasetDetailsById(dataset_id);
    }

    public Object getDatasetDetailsById(UUID dataset_id) {

        return new ResponseEntity( detailsService.findByID(dataset_id), HttpStatus.OK);
    }
}
