package br.com.lukkascost.commons.module.models.entities;

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
@Table(name = "datasets")
@Entity
public class DatasetEntity extends BaseEntity{

    @Column(name = "name")
    private String name ;

    @Column(name = "description")
    private String description  ;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "dataset")
    private Set<SampleEntity> samples = new HashSet<>();

}
