package com.stackroute.helpdesk.controller;


import com.stackroute.helpdesk.entity.Ticket;
import com.stackroute.helpdesk.repository.TicketRepository;
import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

@RestController
@CrossOrigin
public class MailController {

    @Autowired
    private JavaMailSender javaMailSender;


    @Autowired
    private TicketRepository ticketRepository;

    HashMap<String, Object> responseObject;

    // Ticket could not be resolved by CSR, hence callback initiated

    @PatchMapping(path="/tickets/status/callbackmail",  consumes={"application/json"})
    public ResponseEntity<HashMap<String, Object>> changeStatustoCallBackMail(@RequestBody Ticket ticket){
        System.out.println("Ticket: " + ticket);
        Ticket oldTicket = ticketRepository.findById(ticket.getId()).get();

        // To get user email and send an email stating that a callback is initiated (CSR unable to resolve query)
        String email = oldTicket.getUsermail();

        System.out.println("Old Ticket : " + oldTicket);
        //oldTicket.setStatus(ticket.getStatus());
        oldTicket.setStatus("callbackmail");
        ticketRepository.save(oldTicket);
        System.out.println("old ticket after update: " + oldTicket);

        // Sending mail to the user
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("HelpDesk optimus callback mail");
        simpleMailMessage.setText("We will get back to you regarding your query shortly.");
        javaMailSender.send(simpleMailMessage);

        responseObject = new HashMap<>();
        responseObject.put("result", oldTicket);
        responseObject.put("errors", false);
        responseObject.put("message", "Callback initiated!");

        return new ResponseEntity<>(responseObject, HttpStatus.OK);

    }

    // Ticket is resolved

    @PatchMapping(path="/tickets/status/resolved",  consumes={"application/json"})
    public ResponseEntity<HashMap<String, Object>> changeStatustoResolved(@RequestBody Ticket ticket){
        System.out.println("Ticket: " + ticket);
        Ticket oldTicket = ticketRepository.findById(ticket.getId()).get();

        // Get user email
        String email = oldTicket.getUsermail();

        System.out.println("Old Ticket : " + oldTicket);
        //oldTicket.setStatus(ticket.getStatus());
        oldTicket.setStatus("closed");
        ticketRepository.save(oldTicket);
        System.out.println("old ticket after update: " + oldTicket);

        // Sending mail to the user (Stating that the ticket is resolved)
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("HelpDesk optimus Ticket Resolved");
        simpleMailMessage.setText("Your ticket with id "+oldTicket.getId()+" has been resolved!");
        javaMailSender.send(simpleMailMessage);

        responseObject = new HashMap<>();
        responseObject.put("result", oldTicket);
        responseObject.put("errors", false);
        responseObject.put("message", "Ticket resolved!");

        return new ResponseEntity<>(responseObject, HttpStatus.OK);

    }

    // User registers a complaint(A ticket is generated) - to be changed to receive user email
    // after implementing oauth

    @PostMapping(path="/tickets/complaint")
    public ResponseEntity<HashMap<String, Object>> addComplaint(@RequestBody String description){

        Ticket complaint = new Ticket();
        complaint.setDescription(description);
        complaint.setUsermail("lakshyajit165@gmail.com");
        complaint.setStatus("open");
        complaint.setRating(0);
        complaint.setTimeCreated(new Date());
        complaint.setTimeResolved(new Date());
        complaint.setAssignMeTime(new Date());
        complaint.setCommandsUsed(Collections.singletonList("NA"));
        complaint.setType("complaint");
        complaint.setSolvedBy("company");
        complaint.setTags(Collections.singletonList("NA"));

        ticketRepository.save(complaint);

        responseObject = new HashMap<>();
        responseObject.put("result", complaint);
        responseObject.put("errors", false);
        responseObject.put("message", "Ticket generated for complaint!");

        return new ResponseEntity<>(responseObject, HttpStatus.OK);


    }


}
