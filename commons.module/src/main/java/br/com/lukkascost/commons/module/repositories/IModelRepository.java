package br.com.lukkascost.commons.module.repositories;

import br.com.lukkascost.commons.module.models.entities.ClassifierModelCacheEntity;
import org.springframework.data.repository.CrudRepository;

public interface IModelRepository extends CrudRepository<ClassifierModelCacheEntity, String> {
}
