package com.stackroute.helpdesk.ticketservice.service;


import com.stackroute.helpdesk.ticketservice.entity.TicketStructure;
import com.stackroute.helpdesk.ticketservice.repository.TicketRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
@Service
public class PerformanceService implements PerformanceInterface{

    MongoTemplate mongoTemplate;
    private MongoOperations operations;
    public PerformanceService(){ }

    @Autowired
    public PerformanceService(MongoTemplate mongoTemplate, MongoOperations operations) {
        super();
        this.mongoTemplate = mongoTemplate;
        this.operations = operations;
    }

    public JSONObject noOfQueryTaken(){
        List<String> data = new ArrayList<>();
        data.add("open");
        data.add("closed");
        GroupOperation groupOperation = group("assignMeTime").count().as("total");
        MatchOperation filterState =  match((Criteria.where("solvedBy").in("csr1@gmail.com").exists(true)).andOperator(Criteria.where("status").in(data)));
        Aggregation aggregation = newAggregation(filterState,
                groupOperation,project("total").and("_id").as("timestamp").andExclude("_id"));
        //System.out.println("***********" + aggregation);
        AggregationResults<TicketStructure> result = mongoTemplate.aggregate(aggregation,"tickets", TicketStructure.class);
        System.out.println("this is result of aggregate for query taken" + result.getRawResults());
        System.out.println("this is result of aggregate for query taken" + new JSONObject(result.getRawResults()).toJSONString());
        return new JSONObject(result.getRawResults());
    }
    public JSONObject noOfQueryResolved(){
        List<String> data = new ArrayList<>();
        data.add("closed");
        GroupOperation groupOperation = group("assignMeTime").count().as("total");
        MatchOperation filterState =  match((Criteria.where("solvedBy").in("csr1@gmail.com").exists(true)).andOperator(Criteria.where("status").in(data)));
        Aggregation aggregation = newAggregation(filterState,
                groupOperation,project("total").and("_id").as("timestamp").andExclude("_id"));
        //System.out.println("***********" + aggregation);
        AggregationResults<TicketStructure> result = mongoTemplate.aggregate(aggregation,"tickets", TicketStructure.class);
        System.out.println("this is result of aggregate for query taken" + result.getRawResults());
        System.out.println("this is result of aggregate for query taken" + new JSONObject(result.getRawResults()).toJSONString());
        return new JSONObject(result.getRawResults());
    }
}
