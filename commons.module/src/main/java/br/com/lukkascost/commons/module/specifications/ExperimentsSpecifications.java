package br.com.lukkascost.commons.module.specifications;

import br.com.lukkascost.commons.module.models.entities.ExperimentEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class ExperimentsSpecifications {

    public static Specification<ExperimentEntity> withId(UUID uuid) {
        return (root, query, cb) -> uuid == null ? null : cb.equal(root.get("id"), uuid);
    }
    public static Specification<ExperimentEntity> withName(String s) {
        return (root, query, cb) -> s == null ? null : cb.equal(root.get("name"), s);
    }
}