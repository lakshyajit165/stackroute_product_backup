package com.stackroute.helpdesk.controller;

import com.stackroute.helpdesk.entity.Ticket;
import com.stackroute.helpdesk.repository.TicketRepository;
import com.stackroute.helpdesk.service.TicketInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins="http://localhost:4200")
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


    @PatchMapping(path="/tickets/status/resolved",  consumes={"application/json"})
    public ResponseEntity<HashMap<String, Object>> changeStatustoResolved(@RequestBody Ticket ticket){
        System.out.println("Ticket: " + ticket);
        Ticket oldTicket = ticketRepository.findById(ticket.getId()).get();
        System.out.println("Old Ticket : " + oldTicket);
        //oldTicket.setStatus(ticket.getStatus());
        oldTicket.setStatus("closed");
        ticketRepository.save(oldTicket);
        System.out.println("old ticket after update: " + oldTicket);

        responseObject = new HashMap<>();
        responseObject.put("result", oldTicket);
        responseObject.put("errors", false);
        responseObject.put("message", "Ticket resolved");

        return new ResponseEntity<>(responseObject, HttpStatus.OK);

    }

    @PatchMapping(path="/tickets/status/callbackmail",  consumes={"application/json"})
    public ResponseEntity<HashMap<String, Object>> changeStatustoCallBackMail(@RequestBody Ticket ticket){
        System.out.println("Ticket: " + ticket);
        Ticket oldTicket = ticketRepository.findById(ticket.getId()).get();
        System.out.println("Old Ticket : " + oldTicket);
        //oldTicket.setStatus(ticket.getStatus());
        oldTicket.setStatus("callbackmail");
        ticketRepository.save(oldTicket);
        System.out.println("old ticket after update: " + oldTicket);

        responseObject = new HashMap<>();
        responseObject.put("result", oldTicket);
        responseObject.put("errors", false);
        responseObject.put("message", "Ticket goes for callback mail");

        return new ResponseEntity<>(responseObject, HttpStatus.OK);

    }

}
