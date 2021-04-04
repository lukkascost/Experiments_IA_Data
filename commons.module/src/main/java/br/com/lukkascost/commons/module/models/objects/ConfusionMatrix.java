package br.com.lukkascost.commons.module.models.objects;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class ConfusionMatrix {
    private List<String> labels = new ArrayList<>();
    private HashMap<String, HashMap<String, Integer>> confusionMatrix = new HashMap<>();
    private long totalElements = 0;

    public void addLabel(String label){
        if(this.labels.contains(label)) return;
        this.labels.add(label);
        HashMap<String, Integer> col = new HashMap<>();
        this.confusionMatrix.values().forEach(x->{
            x.put(label,0);
        });
        this.labels.forEach(x->col.put(x,0));
        this.confusionMatrix.put(label,col);
    }

    public void add(String predicted, String real){
        add(predicted,real,1);
    }

    public void add(String predicted, String real, int quantity){
        this.totalElements += quantity;
        this.confusionMatrix.get(predicted)
                .replace(real, this.confusionMatrix.get(predicted).get(real) + quantity) ;
    }

    public void add(ConfusionMatrix cm){
        for (String predicted: this.labels) {
            if(!cm.getLabels().contains(predicted)) continue;
            for (String real:this.labels) {
                if(!cm.getLabels().contains(real)) continue;
                add(predicted, real,cm.getConfusionMatrix().get(predicted).get(real) );
            }
        }
    }
}
