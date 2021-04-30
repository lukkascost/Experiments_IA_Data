package br.com.lukkascost.commons.module.models.dto;

import br.com.lukkascost.commons.module.models.objects.ConfusionMatrix;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class RoundDetailsDTO {
    private UUID id;
    private String createdAt;
    private String updatedAt;
    private String name;
    private DatasetDTO dataset;
    private ExperimentsDTO experiment;
    private long executions;

    @JsonIgnore
    private ConfusionMatrix sumConfusionMatrix;

    public long[][] getConfusionMatrix(){
        return this.sumConfusionMatrix.getMatrix();
    }
    public List<String> getLabels(){
        return this.sumConfusionMatrix.getLabels();
    }
    public long getConfusionMatrixTotalElements(){ return this.sumConfusionMatrix.getTotalElements();}

    public float getGeneralAccuracy(){
        return this.getLabels().stream()
                .map(x-> this.sumConfusionMatrix.getConfusionMatrix().get(x).get(x))
                .reduce((o,n) -> o +n).get().floatValue()
                /this.sumConfusionMatrix.getTotalElements();
    }

    public float getBinaryAccuracy(){
        String label = "Class-0";
        if(this.sumConfusionMatrix.getLabels().contains(label)) {
            Integer CP = this.getLabels().stream()
                    .map(x -> this.sumConfusionMatrix.getConfusionMatrix().get(x).get(label))
                    .reduce((o, n) -> o + n).get();
            Integer CN = Math.toIntExact(this.sumConfusionMatrix.getTotalElements() - CP);
            Integer TP = this.sumConfusionMatrix.getConfusionMatrix().get(label).get(label);
            Integer FN = CP - TP;
            Integer PP = this.getLabels().stream()
                    .map(x -> this.sumConfusionMatrix.getConfusionMatrix().get(label).get(x))
                    .reduce((o, n) -> o + n).get();
            Integer FP = PP - TP;
            Integer TN = CN - FP;
            return (TP.floatValue() + TN.floatValue()) / this.sumConfusionMatrix.getTotalElements();
        }
        return 0;
    }
}
