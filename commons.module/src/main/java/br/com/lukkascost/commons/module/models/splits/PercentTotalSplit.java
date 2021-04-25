package br.com.lukkascost.commons.module.models.splits;

import br.com.lukkascost.commons.module.models.dto.SplitterModeDTO;
import br.com.lukkascost.commons.module.models.entities.SampleEntity;
import br.com.lukkascost.commons.module.models.objects.SplitterDataset;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class PercentTotalSplit extends SplitterModeDTO {

    private float percentTrain;

    @Override
    public SplitterDataset split(List<SampleEntity> samples) {
        SplitterDataset result = new SplitterDataset();
        result.setTestSamples(new ArrayList<>());
        result.setTrainSamples(new ArrayList<>());
        List<String> classes = samples.stream().map(x->x.getLabel()).distinct().collect(Collectors.toList());
        for (int i = 0; i < classes.size(); i++) {
            String label = classes.get(i);
            List<SampleEntity> entitiesFromClass = samples.stream().filter(x->x.getLabel().equals(label)).collect(Collectors.toList());
            Collections.shuffle(entitiesFromClass);
            int limit = (int) (this.percentTrain * entitiesFromClass.size());
            result.getTrainSamples().addAll(entitiesFromClass.subList(0, limit)
                    .stream().map(x->x.getId()).collect(Collectors.toList()));
            result.getTestSamples().addAll(entitiesFromClass.subList(limit,entitiesFromClass.size())
                    .stream().map(x->x.getId()).collect(Collectors.toList()));
        }
        return result;
    }
}
