package br.com.lukkascost.api.gateway.configurations;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Primary
@EnableAutoConfiguration
public class DocumentationConfig implements SwaggerResourcesProvider {
    private final EurekaClient discoveryClient;

    public DocumentationConfig(@Qualifier("eurekaClient") EurekaClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @Override
    public List get() {
        List resources = new ArrayList<>();
        List<String> names = discoveryClient.getApplications().getRegisteredApplications().stream().map(x -> x.getName().toLowerCase()).collect(Collectors.toList());
        for (String name : names) {
            resources.add(swaggerResource(name, "/"+name+"/v2/api-docs", "2.0"));
        }
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .docExpansion(DocExpansion.LIST) // or DocExpansion.NONE or DocExpansion.FULL
                .build();
    }

    @Bean
    public Docket api() throws IOException {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.lukkascost.api.gateway.controllers"))
                .paths(PathSelectors.any())
                .build().apiInfo(
                        new ApiInfo("Automatic Api-gateway doc", "Documentation automatically generated", "v1", null, new Contact("Lucas Costa", "https://github.com/lukkascost", "lucas.costa@outlook.com.br"), null, null, new ArrayList())) ;
    }
}