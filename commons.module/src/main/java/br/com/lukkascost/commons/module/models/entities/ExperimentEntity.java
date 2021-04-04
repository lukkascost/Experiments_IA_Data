package br.com.lukkascost.commons.module.models.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "experiments")
@Entity
public class ExperimentEntity extends BaseEntity{
    @Column(name = "name")
    private String name ;

    @Column(name = "description")
    private String description  ;
}
