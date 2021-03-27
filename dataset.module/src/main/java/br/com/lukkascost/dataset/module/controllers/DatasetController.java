package br.com.lukkascost.dataset.module.controllers;

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

    @GetMapping
    public Object getDataset(@RequestParam(required = false) UUID dataset_id) {
        if(dataset_id == null){
            return new ResponseEntity( HttpStatus.OK);
        }
        return getDatasetById(dataset_id);
    }

    public Object getDatasetById(UUID dataset_id) {
       return new ResponseEntity( HttpStatus.CREATED);
    }
}
