package br.com.lukkascost.commons.module.models.objects;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class SplitterDataset {

    private List<UUID> trainSamples;
    private List<UUID> testSamples;
}
