package br.com.lukkascost.commons.module.specifications;

import br.com.lukkascost.commons.module.models.entities.ExecutionEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class ExecutionSpecifications {
    public static Specification<ExecutionEntity> withId(UUID uuid) {
        return (root, query, cb) -> uuid == null ? null : cb.equal(root.get("id"), uuid);
    }
    public static Specification<ExecutionEntity> withRoundId(UUID uuid) {
        return (root, query, cb) -> uuid == null ? null : cb.equal(root.get("round").get("id"), uuid);
    }
}