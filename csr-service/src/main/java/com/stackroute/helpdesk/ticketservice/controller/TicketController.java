package com.stackroute.helpdesk.ticketservice.controller;

import com.stackroute.helpdesk.ticketservice.entity.TicketStructure;
import com.stackroute.helpdesk.ticketservice.exceptions.TicketNotFoundException;
import com.stackroute.helpdesk.ticketservice.repository.TicketRepository;
import com.stackroute.helpdesk.ticketservice.service.TicketInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
@CrossOrigin
public class TicketController {


    TicketInterface ticketInterface;
    //TicketRepository ticketRepository;

    @Autowired
    public TicketController(TicketInterface ticketInterface){
        this.ticketInterface = ticketInterface;
    }

    HashMap<String, Object> responseObject;

    // Get all movies
    @GetMapping(path="/tickets")
    public ResponseEntity<HashMap<String, Object>> getAllTickets(){

        List<TicketStructure> ticketStructures = ticketInterface.getTickets();

        responseObject = new HashMap<>();

        responseObject.put("result", ticketStructures);
        responseObject.put("errors", false);

        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping(path="/tickets/open")
    public ResponseEntity<HashMap<String, Object>> getOpenTickets() {

        responseObject = new HashMap<>();

        try{
            List<TicketStructure> ticketStructures = ticketInterface.getOpenTickets();
            responseObject.put("result", ticketStructures);
            responseObject.put("errors", false);
            responseObject.put("message", "Open tickets found!");
        }catch(Exception e){
            responseObject.put("result", null);
            responseObject.put("errors", false);
            responseObject.put("message", "No Open tickets found!");
        }




//        if(ticketStructures.size() == 0){
//            throw new TicketNotFoundException("No tickets opened now!");
//        }else{
//            responseObject.put("result", ticketStructures);
//            responseObject.put("errors", false);
//            responseObject.put("message", "Open tickets found!");
//        }





        return new ResponseEntity<>(responseObject, HttpStatus.OK);


    }

    @PatchMapping(path="/tickets/status/engage",  consumes={"application/json"})
    public ResponseEntity<HashMap<String, Object>> changeStatustoCallBackMail(@RequestBody TicketStructure ticketStructure){
       // System.out.println("Ticket: " + ticketStructure);

        TicketStructure oldTicket = ticketInterface.getTicketById(ticketStructure.getId()).get();

        // System.out.println("Old Ticket : " + oldTicket);
        //oldTicket.setStatus(ticket.getStatus());

        oldTicket.setStatus("engaged");


        ticketInterface.saveTicketStatus(oldTicket);


//        System.out.println("old ticket after update: " + oldTicket);

        responseObject = new HashMap<>();
        responseObject.put("result", oldTicket);
        responseObject.put("errors", false);
        responseObject.put("message", "Ticket goes to csr or bot for further handling");
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }






}
