package br.com.lukkascost.rounds.module.controllers;


import br.com.lukkascost.commons.module.models.dto.RoundCreateDTO;
import br.com.lukkascost.commons.module.models.dto.RoundDTO;
import br.com.lukkascost.rounds.module.services.IRoundService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/")
public class RoundsController {
    private final IRoundService roundService;

    public RoundsController(IRoundService roundService) {
        this.roundService = roundService;
    }

    @GetMapping
    public ResponseEntity<Page<RoundDTO>> get(RoundDTO dto,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity(roundService.findAll(dto, pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody RoundCreateDTO dto){
        return new ResponseEntity(roundService.create(dto),HttpStatus.CREATED);
    }
}
