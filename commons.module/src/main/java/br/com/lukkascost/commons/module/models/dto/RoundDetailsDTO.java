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
    private double generalAccuracyDeviation;
    private double binaryAccuracyDeviation;


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
        return this.sumConfusionMatrix.getGeneralAccuracy();
    }

    public float getBinaryAccuracy(){
        return this.sumConfusionMatrix.getBinaryAccuracy();
    }
}
