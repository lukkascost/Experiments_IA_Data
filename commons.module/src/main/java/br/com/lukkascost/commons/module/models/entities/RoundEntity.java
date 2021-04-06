package br.com.lukkascost.commons.module.models.entities;

import br.com.lukkascost.commons.module.converters.ConfusionMatrixJsonConverter;
import br.com.lukkascost.commons.module.converters.NormalizationFactorJsonConverter;
import br.com.lukkascost.commons.module.models.objects.ConfusionMatrix;
import br.com.lukkascost.commons.module.models.objects.NormalizationFactor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rounds")
@Entity
public class RoundEntity extends BaseEntity{
    @Column(name = "name")
    private String name ;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "experiment_id", nullable = false)
    private ExperimentEntity experiment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dataset_id", nullable = false)
    private DatasetEntity dataset;

    @Column(name = "sum_confusion_matrix")
    @Convert(converter = ConfusionMatrixJsonConverter.class)
    private ConfusionMatrix sumConfusionMatrix;

    @Column(name = "normalization_limits")
    @Convert(converter = NormalizationFactorJsonConverter.class)
    private NormalizationFactor normalizationFactor;
}
