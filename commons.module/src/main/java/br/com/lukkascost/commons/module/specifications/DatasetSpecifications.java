package br.com.lukkascost.commons.module.specifications;

import br.com.lukkascost.commons.module.models.entities.DatasetEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class DatasetSpecifications {

    public static Specification<DatasetEntity> withId(UUID uuid) {
        return (root, query, cb) -> uuid == null ? null : cb.equal(root.get("id"), uuid);
    }
    public static Specification<DatasetEntity> withName(String s) {
        return (root, query, cb) -> s == null ? null : cb.equal(root.get("name"), s);
    }
}