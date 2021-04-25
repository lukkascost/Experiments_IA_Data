package br.com.lukkascost.commons.module.models.entities;

import br.com.lukkascost.commons.module.converters.ConfusionMatrixJsonConverter;
import br.com.lukkascost.commons.module.converters.SplitterDatasetConverterJson;
import br.com.lukkascost.commons.module.models.objects.ConfusionMatrix;
import br.com.lukkascost.commons.module.models.objects.SplitterDataset;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "executions")
@Entity
public class ExecutionEntity extends BaseEntity {

    @Column(name = "model_id")
    private UUID modelId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "round_id", nullable = false)
    private RoundEntity round;

    @Column(name = "split_dataset")
    @Convert(converter = SplitterDatasetConverterJson.class)
    private SplitterDataset splitDataset;

    @Column(name = "confusion_matrix")
    @Convert(converter = ConfusionMatrixJsonConverter.class)
    private ConfusionMatrix confusionMatrix;

}
