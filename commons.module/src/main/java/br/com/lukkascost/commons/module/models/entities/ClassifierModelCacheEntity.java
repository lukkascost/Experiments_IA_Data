package br.com.lukkascost.commons.module.models.entities;

import br.com.lukkascost.commons.module.models.enuns.ClassifierType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

@Getter
@Setter
@RedisHash("Models")
public class ClassifierModelCacheEntity implements Serializable {
    private UUID id;
    private String model;
    private HashMap<String, String> params;
    private ClassifierType classifierType;
    private HashMap<UUID,String> sampleResult;
}
