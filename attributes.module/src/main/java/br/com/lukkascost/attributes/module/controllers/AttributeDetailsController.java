package br.com.lukkascost.attributes.module.controllers;


import br.com.lukkascost.attributes.module.mappers.AttributeMapper;
import br.com.lukkascost.attributes.module.services.IAttributeService;
import br.com.lukkascost.commons.module.models.entities.AttributeEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/")
public class AttributeDetailsController {


    private final AttributeMapper attributeMapper;
    private final IAttributeService attributeService;

    public AttributeDetailsController(AttributeMapper attributeMapper, IAttributeService attributeService) {
        this.attributeMapper = attributeMapper;
        this.attributeService = attributeService;
    }

    @GetMapping
    public Object getAttributes(@RequestParam(required = false) UUID sample_id) {
        List<AttributeEntity> attributeEntityList;
        if( sample_id == null){
             attributeEntityList = attributeService.findAll();
            return new ResponseEntity(attributeMapper.convert(attributeEntityList),HttpStatus.OK);
        }

        attributeEntityList = attributeService.findAllBySampleId(sample_id);
        return new ResponseEntity(attributeMapper.convert(attributeEntityList), HttpStatus.OK);
    }
}
