package br.com.lukkascost.commons.module.specifications;

import br.com.lukkascost.commons.module.models.entities.RoundEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class RoundSpecifications {
    public static Specification<RoundEntity> withId(UUID uuid) {
        return (root, query, cb) -> uuid == null ? null : cb.equal(root.get("id"), uuid);
    }
    public static Specification<RoundEntity> withDatasetId(UUID uuid) {
        return (root, query, cb) -> uuid == null ? null : cb.equal(root.get("dataset").get("id"), uuid);
    }
    public static Specification<RoundEntity> withExperimentId(UUID uuid) {
        return (root, query, cb) -> uuid == null ? null : cb.equal(root.get("experiment").get("id"), uuid);
    }
}