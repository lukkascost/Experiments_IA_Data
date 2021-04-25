package br.com.lukkascost.rounds.module.controllers;


import br.com.lukkascost.commons.module.models.dto.RoundDetailsDTO;
import br.com.lukkascost.rounds.module.services.IRoundService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "details/")
public class RoundDetailsController {
    private final IRoundService roundService;

    public RoundDetailsController(IRoundService roundService) {
        this.roundService = roundService;
    }

    @GetMapping
    public ResponseEntity<Page<RoundDetailsDTO>> get(RoundDetailsDTO dto,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity(roundService.findAll(dto, pageable), HttpStatus.OK);
    }
}
