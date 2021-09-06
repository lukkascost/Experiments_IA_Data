package br.com.lukkascost.commons.module.mappers;

import br.com.lukkascost.commons.module.models.dto.RoundCreateDTO;
import br.com.lukkascost.commons.module.models.dto.RoundDTO;
import br.com.lukkascost.commons.module.models.dto.RoundDetailsDTO;
import br.com.lukkascost.commons.module.models.entities.ExecutionEntity;
import br.com.lukkascost.commons.module.models.entities.RoundEntity;
import br.com.lukkascost.commons.module.specifications.RoundSpecifications;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class RoundModelMapper {

    public abstract RoundDTO convert(RoundEntity roundEntity);

    public abstract List<RoundDTO> convert(List<RoundEntity> entityList);
    public abstract List<RoundDetailsDTO> convertDetails(List<RoundEntity> entityList);
    public long map(Set<ExecutionEntity> past) {
        return past.size();
    }


    public abstract RoundDTO convert(RoundCreateDTO dto);

    public abstract RoundEntity convertEntity(RoundCreateDTO dto);

    @Mappings({
            @Mapping(target="generalAccuracyDeviation", source="executions",  qualifiedByName = "deviationGeneral"),
            @Mapping(target="binaryAccuracyDeviation", source="executions",  qualifiedByName = "deviationBinary"),
    })
    public abstract RoundDetailsDTO convertDetails(RoundEntity entity);

    public Specification<RoundEntity> convert(RoundDTO dto){
        Specification<RoundEntity> spec = Specification.where(RoundSpecifications.withId(dto.getId()))
                .and(RoundSpecifications.withName(dto.getName()));

        if(dto.getDataset() != null) spec = spec.and(RoundSpecifications.withDatasetId(dto.getDataset().getId()));
        if(dto.getExperiment() != null) spec = spec.and(RoundSpecifications.withExperimentId(dto.getExperiment().getId()));
        return spec;
    }

    public Specification<RoundEntity> convert(RoundDetailsDTO dto){
        Specification<RoundEntity> spec = Specification.where(RoundSpecifications.withId(dto.getId()))
                .and(RoundSpecifications.withName(dto.getName()));
        if(dto.getDataset() != null) spec = spec.and(RoundSpecifications.withDatasetId(dto.getDataset().getId()));
        if(dto.getExperiment() != null) spec = spec.and(RoundSpecifications.withExperimentId(dto.getExperiment().getId()));
        return spec;
    }

    public PageImpl<RoundDetailsDTO> convertDetails(Page<RoundEntity> page){
        return new PageImpl(this.convertDetails(page.getContent()),page.getPageable(),page.getTotalElements());
    }

    public PageImpl<RoundDTO> convert(Page<RoundEntity> page){
        return new PageImpl(this.convert(page.getContent()),page.getPageable(),page.getTotalElements());
    }


    @Named("deviationGeneral")
    public static double deviationGeneral(Set<ExecutionEntity> executions) {
        List<Float> accs = executions.stream().map(x->(x.getConfusionMatrix().getGeneralAccuracy() )).collect(Collectors.toList());
        double sum = 0.0, standardDeviation = 0.0;
        int length = accs.size();
        for(Float num : accs) {
            sum += num;
        }
        double mean = sum/length;
        for(double num: accs) {
            standardDeviation += Math.pow(num - mean, 2);
        }
        return Math.sqrt(standardDeviation/length);

    }
    @Named("deviationBinary")
    public static double deviationBinary(Set<ExecutionEntity> executions) {
        List<Float> accs = executions.stream().map(x->(x.getConfusionMatrix().getBinaryAccuracy() )).collect(Collectors.toList());
        double sum = 0.0, standardDeviation = 0.0;
        int length = accs.size();
        for(Float num : accs) {
            sum += num;
        }
        double mean = sum/length;
        for(double num: accs) {
            standardDeviation += Math.pow(num - mean, 2);
        }
        return Math.sqrt(standardDeviation/length);
    }

}
