package br.com.lukkascost.commons.module.models.objects;


import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class NormalizationFactor {
    private HashMap<String, Double> downLimit = new HashMap<>();
    private HashMap<String, Double> upLimit = new HashMap<>();
}
