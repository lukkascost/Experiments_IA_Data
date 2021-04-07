package br.com.lukkascost.commons.module.models.dto;

import br.com.lukkascost.commons.module.models.objects.ConfusionMatrix;
import br.com.lukkascost.commons.module.models.objects.SplitterDataset;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ExecutionDetailsDTO {
    private UUID id;
    private String createdAt;
    private String updatedAt;
    private UUID modelId;
    private RoundDTO round;
    private SplitterDataset splitDataset;
    private ConfusionMatrix confusionMatrix;
}
