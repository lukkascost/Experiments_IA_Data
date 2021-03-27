package br.com.lukkascost.dataset.module.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "datails")
public class DatasetDetailsController {

    @GetMapping
    public Object getDatasetDetails(@RequestParam(required = false) UUID dataset_id) {
        if(dataset_id == null){
            return new ResponseEntity( HttpStatus.OK);
        }
        return getDatasetDetailsById(dataset_id);
    }

    public Object getDatasetDetailsById(UUID dataset_id) {
        return new ResponseEntity( HttpStatus.CREATED);
    }
}
