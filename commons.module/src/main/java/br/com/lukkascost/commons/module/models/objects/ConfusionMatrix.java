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

    public long[][] getMatrix() {
        long[][] result = new long[this.labels.size()][this.labels.size()];
        for (int i = 0; i < this.labels.size(); i++) {
            for (int j = 0; j < this.labels.size(); j++) {
                result[i][j] = this.confusionMatrix.get(this.labels.get(i)).get(this.labels.get(j));
            }
        }
        return result;
    }

    public float getGeneralAccuracy(){
        return this.labels.stream().map(x->this.confusionMatrix.get(x).get(x)).reduce((o,n)-> o+n).get() /
                ((float)this.totalElements);
    }

    public float getBinaryAccuracy(){
        String label = "Class-0";
        if(this.labels.contains(label)) {
            Integer CP = this.labels.stream()
                    .map(x -> this.confusionMatrix.get(x).get(label))
                    .reduce((o, n) -> o + n).get();
            Integer CN = Math.toIntExact(this.totalElements - CP);
            Integer TP = this.confusionMatrix.get(label).get(label);
            Integer FN = CP - TP;
            Integer PP = this.getLabels().stream()
                    .map(x -> this.confusionMatrix.get(label).get(x))
                    .reduce((o, n) -> o + n).get();
            Integer FP = PP - TP;
            Integer TN = CN - FP;
            return (TP.floatValue() + TN.floatValue()) / this.totalElements;
        }
        return 0;
    }
}
