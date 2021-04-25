package br.com.lukkascost.commons.module.converters;

import br.com.lukkascost.commons.module.models.objects.SplitterDataset;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

import javax.persistence.AttributeConverter;
import java.io.IOException;

@Log4j2
public class SplitterDatasetConverterJson implements AttributeConverter<SplitterDataset, String> {

    private final ObjectMapper objectMapper;

    public SplitterDatasetConverterJson(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    public String convertToDatabaseColumn(SplitterDataset splitterDataset) {
        String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(splitterDataset);
        } catch (final JsonProcessingException e) {
            log.error("JSON writing error", e);
        }
        return customerInfoJson;
    }

    @Override
    public SplitterDataset convertToEntityAttribute(String s) {
        SplitterDataset customerInfo = null;
        try {
            customerInfo = objectMapper.readValue(s, SplitterDataset.class);
        } catch (final IOException e) {
            log.error("JSON reading error", e);
        }
        return customerInfo;
    }
}
