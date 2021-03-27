package br.com.eletra.api.gateway.service.impl;

import br.com.eletra.constants.ServiceNames;
import br.com.lukkascost.api.gateway.service.impl.MonitorService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

class MonitorServiceTest extends TestCase  {

    @Mock
    private EurekaClient discoveryClient;

    @Mock
    private Applications applications;

    @Mock
    private Application application;

    private MonitorService service;
    private InstanceInfo expectedInstanceTest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.service= new MonitorService(discoveryClient);
         expectedInstanceTest = new  InstanceInfo("ID", "Test", "GROUP_NAME", "IPADDR", "SID", new InstanceInfo.PortWrapper(true, 8080),
                 new InstanceInfo.PortWrapper(true, 8090),  "homePageUrl", "statusPageUrl",  "healthCheckUrl",
                 "secureHealthCheckUrl",   "vipAddress", "secureVipAddress",0, null, "hostName", InstanceInfo.InstanceStatus.UP,
                 com.netflix.appinfo.InstanceInfo.InstanceStatus.UP,  com.netflix.appinfo.InstanceInfo.InstanceStatus.UP, null, true, null, 1L, 1L,
        com.netflix.appinfo.InstanceInfo.ActionType.ADDED, "asgName");

        Mockito.doAnswer(x-> applications).when(discoveryClient).getApplications();

        Mockito.doAnswer(x-> application).when(applications).getRegisteredApplications("Test");

        Mockito.doAnswer(x-> Collections.singletonList(expectedInstanceTest)).when(application).getInstancesAsIsFromEureka();
    }

    @Test
    public void getServicesNamesRegistered() {
        Mockito.doAnswer(x-> Collections.singletonList(new Application("TEST"))).when(applications).getRegisteredApplications();

        List<String> result =  this.service.getServicesNames(true);
        Assert.assertArrayEquals(new String[]{"test"}, result.toArray() );
    }
    @Test
    public void getServicesNamesNotRegistered() {
        List<String> result =  this.service.getServicesNames(false);
        Assert.assertArrayEquals(ServiceNames.services, result.toArray() );
    }

    @Test
    public void getInstances() {

        List<InstanceInfo> result =  this.service.getInstances("Test");
        Assert.assertArrayEquals(Collections.singletonList(expectedInstanceTest).toArray(),result.toArray());

    }

    @Test
    public void getInstancesDoesNotExists() {

        List<InstanceInfo> result =  this.service.getInstances("Inexistent");
        Assert.assertArrayEquals(Collections.emptyList().toArray(), result.toArray());

    }
}