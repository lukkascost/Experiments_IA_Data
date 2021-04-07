package br.com.lukkascost.attributes.module.controllers;


import br.com.lukkascost.attributes.module.services.IAttributeService;
import br.com.lukkascost.commons.module.models.dto.AttributeCreateDTO;
import br.com.lukkascost.commons.module.models.dto.AttributeDetailsDTO;
import br.com.lukkascost.commons.module.models.dto.AttributeValueDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/")
public class AttributeDetailsController {


    private final IAttributeService attributeService;

    public AttributeDetailsController(IAttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @GetMapping
    public ResponseEntity<Page<AttributeDetailsDTO>> get(AttributeDetailsDTO executionDetailsDTO, @RequestParam(required = false) UUID sample_id, @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity(attributeService.findAll(executionDetailsDTO, sample_id, pageable), HttpStatus.OK);
    }

    @GetMapping("/values")
    public ResponseEntity<AttributeValueDTO> getValues(AttributeDetailsDTO attributeDetailsDTO, @RequestParam UUID sample_id, @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity(attributeService.findAllValues(attributeDetailsDTO, sample_id, pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createSample(@RequestBody AttributeCreateDTO dto){
        return new ResponseEntity(attributeService.create(dto),HttpStatus.CREATED);
    }
}
