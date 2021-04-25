package br.com.lukkascost.commons.module.converters;

import br.com.lukkascost.commons.module.models.objects.NormalizationFactor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

import javax.persistence.AttributeConverter;
import java.io.IOException;

@Log4j2
public class NormalizationFactorJsonConverter  implements AttributeConverter<NormalizationFactor, String>  {
    private final ObjectMapper objectMapper;

    public NormalizationFactorJsonConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    public String convertToDatabaseColumn(NormalizationFactor normalizationFactor) {
        String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(normalizationFactor);
        } catch (final JsonProcessingException e) {
            log.error("JSON writing error", e);
        }
        return customerInfoJson;
    }

    @Override
    public NormalizationFactor convertToEntityAttribute(String s) {
        NormalizationFactor customerInfo = null;
        try {
            customerInfo = objectMapper.readValue(s, NormalizationFactor.class);
        } catch (final IOException e) {
            log.error("JSON reading error", e);
        }
        return customerInfo;
    }
}
