package com.stackroute.helpdesk.controller;

import com.stackroute.helpdesk.entity.Ticket;
import com.stackroute.helpdesk.repository.TicketRepository;
import com.stackroute.helpdesk.service.TicketInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController()
public class TicketController {

    TicketInterface ticketInterface;
    TicketRepository ticketRepository;

    @Autowired
    public TicketController(TicketInterface ticketInterface, TicketRepository ticketRepository){
        this.ticketInterface = ticketInterface;
        this.ticketRepository = ticketRepository;
    }

    HashMap<String, Object> responseObject;

    // Get all movies
    @GetMapping(path="/tickets")
    public ResponseEntity<HashMap<String, Object>> getAllTickets(){

        List<Ticket> tickets = ticketInterface.getTickets();

        responseObject = new HashMap<>();

        responseObject.put("result", tickets);
        responseObject.put("errors", false);

        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping(path="/tickets/open")
    public ResponseEntity<HashMap<String, Object>> getOpenTickets(){

        List<Ticket> tickets = ticketInterface.getOpenTickets();

        responseObject = new HashMap<>();

        if(tickets.size() == 0){
            responseObject.put("result", "No open tickets found!");
            responseObject.put("errors", false);
            responseObject.put("message", "No open tickets!");
        }else{
            responseObject.put("result", tickets);
            responseObject.put("errors", false);
            responseObject.put("message", "Open tickets found!");
        }


        return new ResponseEntity<>(responseObject, HttpStatus.OK);


    }

    @PostMapping(path="/tickets/complaint",  consumes={"application/json"})
    public ResponseEntity<HashMap<String, Object>> addComplaint(@RequestBody Ticket ticket){
        ticketRepository.save(ticket);

        responseObject = new HashMap<>();
        responseObject.put("result", ticket);
        responseObject.put("errors", false);
        responseObject.put("message", "Ticket generated for complaint!");

        return new ResponseEntity<>(responseObject, HttpStatus.OK);

    }
}
