package br.com.lukkascost.commons.module.models.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attributes")
@Entity
public class AttributeEntity extends BaseEntity{

    @Column(name = "value")
    private String value ;

    @Column(name = "order")
    private int order ;

    @Column(name = "name")
    private String Name ;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "sample_id", nullable = false)
    private SampleEntity sample;

}
