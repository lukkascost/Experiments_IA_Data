package br.com.lukkascost.commons.module.specifications;

import br.com.lukkascost.commons.module.models.entities.AttributeEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class AttributeSpecifications {
    public static Specification<AttributeEntity> withSampleId(UUID datasetId) {
        return (root, query, cb) -> datasetId == null ? null : cb.equal(root.get("sample").get("id"), datasetId);
    }

    public static Specification<AttributeEntity> withId(UUID sampleId) {
        return (root, query, cb) -> sampleId == null ? null : cb.equal(root.get("id"), sampleId);
    }
    public static Specification<AttributeEntity> withName(String s) {
        return (root, query, cb) -> s == null ? null : cb.equal(root.get("name"), s);
    }
}