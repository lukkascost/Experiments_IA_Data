package br.com.lukkascost.api.gateway.filter;

import br.com.lukkascost.api.gateway.filters.HexingMdcConnectorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulFilters {

    @Bean
    public HexingMdcConnectorFilter HexingMdcConnectorFilter() {
        return new HexingMdcConnectorFilter();
    }

}
