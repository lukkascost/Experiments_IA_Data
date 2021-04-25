package br.com.lukkascost.dataset.module.controllers;

import br.com.lukkascost.commons.module.models.dto.DatasetCreateDTO;
import br.com.lukkascost.commons.module.models.dto.DatasetDTO;
import br.com.lukkascost.dataset.module.services.IDatasetService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/")
public class DatasetController {

    private final IDatasetService datasetService;

    public DatasetController(IDatasetService datasetService) {
        this.datasetService = datasetService;
    }

    @GetMapping
    public ResponseEntity<Page<DatasetDTO>> getDataset(DatasetDTO datasetDTO,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity(datasetService.findAll(datasetDTO, pageable), HttpStatus.OK);
    }

    @GetMapping("extract/{id}")
    public ResponseEntity extract(@PathVariable(name = "id") UUID id){
        datasetService.extract(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createDataset(@RequestBody DatasetCreateDTO dto){
        return new ResponseEntity(datasetService.create(dto),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity updateDataset(@RequestBody DatasetDTO dto){
        return new ResponseEntity(datasetService.update(dto),HttpStatus.OK);
    }


    @DeleteMapping
    public ResponseEntity deleteDataset(@RequestParam UUID id){
        return new ResponseEntity(datasetService.delete(id),HttpStatus.OK);
    }
}
