package br.com.lukkascost.sample.module.controllers;

import br.com.lukkascost.commons.module.models.dto.SampleCreateDTO;
import br.com.lukkascost.commons.module.models.dto.SampleDTO;
import br.com.lukkascost.sample.module.services.ISampleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/")
public class SampleController {

    private final ISampleService sampleService;

    public SampleController(ISampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping
    public ResponseEntity<Page<SampleDTO>> getSample(SampleDTO sampleDTO, @RequestParam(required = false) UUID dataset_id, @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
            return new ResponseEntity(sampleService.findAll(sampleDTO,dataset_id, pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createSample(@RequestBody SampleCreateDTO dto){
        return new ResponseEntity(sampleService.create(dto),HttpStatus.CREATED);
    }

}
