package br.com.lukkascost.api.gateway.controllers;

import br.com.lukkascost.api.gateway.service.IMonitorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MonitorController {
    private final IMonitorService monitorService;

    public MonitorController(IMonitorService monitorService) {
        this.monitorService = monitorService;
    }


    @GetMapping("/services")
    public Object getServices(@RequestParam(required = false, defaultValue = "false") boolean registered, @RequestParam(required = false, defaultValue = "") String instance_name) {
        if("".equals(instance_name)){
            return new ResponseEntity<>(monitorService.getServicesNames(registered), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(monitorService.getInstances(instance_name), HttpStatus.OK);
        }
    }


}
