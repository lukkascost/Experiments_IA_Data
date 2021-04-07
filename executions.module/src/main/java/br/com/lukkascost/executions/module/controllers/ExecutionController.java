package br.com.lukkascost.executions.module.controllers;


import br.com.lukkascost.commons.module.models.dto.ExecutionCreateDTO;
import br.com.lukkascost.commons.module.models.dto.ExecutionDetailsDTO;
import br.com.lukkascost.executions.module.services.IExecutionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
public class ExecutionController {
private final IExecutionService executionService;

    public ExecutionController(IExecutionService executionService) {
        this.executionService = executionService;
    }

    @GetMapping
    public ResponseEntity<Page<ExecutionDetailsDTO>> get(ExecutionDetailsDTO executionDetailsDTO, @RequestParam(required = false) UUID round_id, @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity(executionService.findAll(executionDetailsDTO, round_id, pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody ExecutionCreateDTO dto){
        return new ResponseEntity(executionService.create(dto),HttpStatus.CREATED);
    }
}
