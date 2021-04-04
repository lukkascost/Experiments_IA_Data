package br.com.lukkascost.commons.module.converters;

import br.com.lukkascost.commons.module.models.objects.ConfusionMatrix;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

import javax.persistence.AttributeConverter;
import java.io.IOException;

@Log4j2
public class ConfusionMatrixJsonConverter implements AttributeConverter<ConfusionMatrix, String> {

    private final ObjectMapper objectMapper;

    public ConfusionMatrixJsonConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    public String convertToDatabaseColumn(ConfusionMatrix confusionMatrix) {
        String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(confusionMatrix);
        } catch (final JsonProcessingException e) {
            log.error("JSON writing error", e);
        }
        return customerInfoJson;
    }

    @Override
    public ConfusionMatrix convertToEntityAttribute(String s) {
        ConfusionMatrix customerInfo = null;
        try {
            customerInfo = objectMapper.readValue(s, ConfusionMatrix.class);
        } catch (final IOException e) {
            log.error("JSON reading error", e);
        }
        return customerInfo;
    }
}
