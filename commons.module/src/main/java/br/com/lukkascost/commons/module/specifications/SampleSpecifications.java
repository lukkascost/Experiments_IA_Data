package br.com.lukkascost.commons.module.specifications;

import br.com.lukkascost.commons.module.models.entities.SampleEntity;
import br.com.lukkascost.commons.module.models.enuns.ExtractorType;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class SampleSpecifications {
    public static Specification<SampleEntity> withDatasetId(UUID datasetId) {
        return (root, query, cb) -> datasetId == null ? null : cb.equal(root.get("dataset").get("id"), datasetId);
    }

    public static Specification<SampleEntity> withId(UUID sampleId) {
        return (root, query, cb) -> sampleId == null ? null : cb.equal(root.get("id"), sampleId);
    }

    public static Specification<SampleEntity> withExtractorType(ExtractorType extractorType) {
        return (root, query, cb) -> extractorType == null ? null : cb.equal(root.get("extractorType"), extractorType);
    }
    public static Specification<SampleEntity> withOriginalFileName(String s) {
        return (root, query, cb) -> s == null ? null : cb.equal(root.get("originalFileName"), s);
    }
    public static Specification<SampleEntity> withLabel(String s) {
        return (root, query, cb) -> s == null ? null : cb.equal(root.get("label"), s);
    }
}