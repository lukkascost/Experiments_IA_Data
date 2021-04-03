package br.com.lukkascost.commons.module.models.entities;


import br.com.lukkascost.commons.module.models.enuns.ExtractorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "samples")
@Entity
public class SampleEntity extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(name = "extractor_type", length = 100)
    private ExtractorType extractorType;

    @Column(name = "original_file_name")
    public String originalFileName ;

    @Column(name = "file_id")
    public String fileId;

    @Column(name = "label")
    public String label ;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "dataset_id", nullable = false)
    private DatasetEntity dataset;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sample")
    private Set<AttributeEntity> attributes = new HashSet<>();
}
