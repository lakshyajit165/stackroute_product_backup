package com.stackroute.helpdesk.ticketservice.service;

import org.json.simple.JSONObject;

import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceInterface {
    public JSONObject noOfQueryTaken();
    public JSONObject noOfQueryResolved();
}