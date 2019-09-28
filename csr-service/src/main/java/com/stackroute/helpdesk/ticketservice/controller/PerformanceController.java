package com.stackroute.helpdesk.ticketservice.controller;


import com.stackroute.helpdesk.ticketservice.service.PerformanceInterface;
import com.stackroute.helpdesk.ticketservice.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
@RestController()
@CrossOrigin(origins="http://localhost:4200")
public class PerformanceController {

    PerformanceInterface performanceInterface;
    PerformanceService performanceService;

    @Autowired
    public PerformanceController(PerformanceInterface performanceInterface, PerformanceService performanceService) {
        this.performanceInterface = performanceInterface;
        this.performanceService = performanceService;
    }
    HashMap<String, Object> responseObject;
    @GetMapping(path="/tickets/csr/taken")
    public ResponseEntity<HashMap<String, Object>> getAssignTimeTaken(){
        responseObject = new HashMap<>();
        responseObject.put("result", performanceInterface.noOfQueryTaken());
        responseObject.put("errors", false);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @GetMapping(path="/tickets/csr/resolved")
    public ResponseEntity<HashMap<String, Object>> getAssignTimeResolved(){
        responseObject = new HashMap<>();
        responseObject.put("result", performanceInterface.noOfQueryResolved());
        responseObject.put("errors", false);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
