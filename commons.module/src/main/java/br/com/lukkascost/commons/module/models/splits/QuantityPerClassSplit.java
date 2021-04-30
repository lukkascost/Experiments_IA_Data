package br.com.lukkascost.commons.module.models.splits;


import br.com.lukkascost.commons.module.models.dto.SplitterModeDTO;
import br.com.lukkascost.commons.module.models.entities.SampleEntity;
import br.com.lukkascost.commons.module.models.objects.SplitterDataset;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class QuantityPerClassSplit extends SplitterModeDTO {

    private List<String> classes;
    private HashMap<String,Integer> percentTrainPerClass;

    @Override
    public SplitterDataset split(List<SampleEntity> samples) {
        return null;
    }
}
