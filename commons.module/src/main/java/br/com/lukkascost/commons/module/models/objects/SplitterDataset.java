package br.com.lukkascost.commons.module.models.objects;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SplitterDataset {

    private List<String> trainSamples;
    private List<String> testSamples;
}
