package br.com.lukkascost.commons.module.models.dto;


import br.com.lukkascost.commons.module.models.entities.SampleEntity;
import br.com.lukkascost.commons.module.models.enuns.SplitType;
import br.com.lukkascost.commons.module.models.objects.SplitterDataset;
import br.com.lukkascost.commons.module.models.splits.PercentPerClassSplit;
import br.com.lukkascost.commons.module.models.splits.PercentTotalSplit;
import br.com.lukkascost.commons.module.models.splits.QuantityPerClassSplit;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import java.util.List;

import static br.com.lukkascost.commons.module.models.enuns.SplitType.TypeConstants.*;

@JsonTypeInfo(
        use=JsonTypeInfo.Id.NAME,
        include= JsonTypeInfo.As.EXISTING_PROPERTY,
        visible = true,
        property="splitType")

@JsonSubTypes({
        @JsonSubTypes.Type(value = PercentTotalSplit.class, name = by_percent_total),
        @JsonSubTypes.Type(value = PercentPerClassSplit.class, name = by_percent_per_class),
        @JsonSubTypes.Type(value = QuantityPerClassSplit.class, name = by_quantity)
})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public abstract class SplitterModeDTO {
    protected SplitType splitType;

    public abstract SplitterDataset split(List<SampleEntity> samples);
}
