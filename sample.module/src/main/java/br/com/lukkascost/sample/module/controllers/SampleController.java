package br.com.lukkascost.sample.module.controllers;

import br.com.lukkascost.commons.module.models.dto.SampleDTO;
import br.com.lukkascost.sample.module.services.ISampleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/")
public class SampleController {

    private final ISampleService sampleService;

    public SampleController(ISampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping
    public Object getSample(SampleDTO sampleDTO, @RequestParam(required = false) UUID dataset_id) {
            return new ResponseEntity(sampleService.findAll(sampleDTO, dataset_id), HttpStatus.OK);
    }
}
