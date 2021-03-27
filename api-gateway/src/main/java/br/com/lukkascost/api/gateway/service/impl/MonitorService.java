package br.com.lukkascost.api.gateway.service.impl;

import br.com.eletra.constants.ServiceNames;
import br.com.lukkascost.api.gateway.service.IMonitorService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonitorService implements IMonitorService {
    private final EurekaClient discoveryClient;

    public MonitorService(@Qualifier("eurekaClient") EurekaClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }


    public List<String> getServicesNames(boolean registered) {
        if(registered){
            return discoveryClient.getApplications().getRegisteredApplications().stream().map(x->x.getName().toLowerCase()).collect(Collectors.toList());
        }
        else {
            return Arrays.asList(ServiceNames.services);
        }
    }

    public List<InstanceInfo> getInstances(String instance_name) {
        Application application = discoveryClient.getApplications().getRegisteredApplications(instance_name);
        if(application == null ) return new ArrayList<>();
        return application.getInstancesAsIsFromEureka();
    }
}
