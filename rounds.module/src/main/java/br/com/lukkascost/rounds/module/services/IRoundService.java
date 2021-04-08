package br.com.lukkascost.rounds.module.services;

import br.com.lukkascost.commons.module.models.dto.RoundCreateDTO;
import br.com.lukkascost.commons.module.models.dto.RoundDTO;
import br.com.lukkascost.commons.module.models.dto.RoundDetailsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IRoundService {
    Page<RoundDTO> findAll(RoundDTO dto, Pageable pageable);

    RoundDTO create(RoundCreateDTO dto);

    RoundDetailsDTO doCompressResult(UUID round_id);
}
