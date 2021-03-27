package br.com.lukkascost.api.gateway.service;

import com.netflix.appinfo.InstanceInfo;

import java.util.List;

public interface IMonitorService {
    List<InstanceInfo> getInstances(String instance_name);

    List<String> getServicesNames(boolean registered);

}
