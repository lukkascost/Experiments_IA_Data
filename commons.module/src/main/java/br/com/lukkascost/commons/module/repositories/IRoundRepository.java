package br.com.lukkascost.commons.module.repositories;

import br.com.lukkascost.commons.module.models.entities.RoundEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface IRoundRepository extends JpaRepository<RoundEntity, UUID> , JpaSpecificationExecutor<RoundEntity> {

}
