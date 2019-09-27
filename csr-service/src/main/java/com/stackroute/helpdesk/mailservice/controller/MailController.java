package com.stackroute.helpdesk.mailservice.controller;


import com.stackroute.helpdesk.ticketservice.entity.Response;
import com.stackroute.helpdesk.ticketservice.entity.TicketStructure;
import com.stackroute.helpdesk.ticketservice.repository.TicketRepository;


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
public class MailController{

    @Autowired
    private JavaMailSender javaMailSender;


    @Autowired
    private TicketRepository ticketRepository;

    HashMap<String, Object> responseObject;

    // TicketStructure could not be resolved by CSR, hence callback initiated

    @PatchMapping(path="/tickets/status/callbackmail",  consumes={"application/json"})
    public ResponseEntity<HashMap<String, Object>> changeStatustoCallBackMail(@RequestBody TicketStructure ticketStructure){
        System.out.println("TicketStructure: " + ticketStructure);
        TicketStructure oldTicketStructure = ticketRepository.findById(ticketStructure.getId()).get();

        // To get user email and send an email stating that a callback is initiated (CSR unable to resolve query)
        String email = oldTicketStructure.getUsermail();

        System.out.println("Old TicketStructure : " + oldTicketStructure);
        //oldTicketStructure.setStatus(ticketStructure.getStatus());
        oldTicketStructure.setStatus("callbackmail");
        ticketRepository.save(oldTicketStructure);
        System.out.println("old ticketStructure after update: " + oldTicketStructure);

        // Sending mail to the user
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("HelpDesk optimus callback mail");
        simpleMailMessage.setText("We will get back to you regarding your query shortly.");
        javaMailSender.send(simpleMailMessage);

        responseObject = new HashMap<>();
        responseObject.put("result", oldTicketStructure);
        responseObject.put("errors", false);
        responseObject.put("message", "Callback initiated!");

        return new ResponseEntity<>(responseObject, HttpStatus.OK);

    }

    // TicketStructure is resolved

    @PatchMapping(path="/tickets/status/resolved",  consumes={"application/json"})
    public ResponseEntity<HashMap<String, Object>> changeStatustoResolved(@RequestBody TicketStructure ticketStructure){
        System.out.println("TicketStructure: " + ticketStructure);
        TicketStructure oldTicketStructure = ticketRepository.findById(ticketStructure.getId()).get();

        // Get user email
        String email = oldTicketStructure.getUsermail();

        System.out.println("Old TicketStructure : " + oldTicketStructure);
        //oldTicketStructure.setStatus(ticketStructure.getStatus());
        oldTicketStructure.setStatus("closed");
        ticketRepository.save(oldTicketStructure);
        System.out.println("old ticketStructure after update: " + oldTicketStructure);

        // Sending mail to the user (Stating that the ticketStructure is resolved)
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("HelpDesk optimus TicketStructure Resolved");
        simpleMailMessage.setText("Your ticketStructure with id "+ oldTicketStructure.getId()+" has been resolved!");
        javaMailSender.send(simpleMailMessage);

        responseObject = new HashMap<>();
        responseObject.put("result", oldTicketStructure);
        responseObject.put("errors", false);
        responseObject.put("message", "TicketStructure resolved!");

        return new ResponseEntity<>(responseObject, HttpStatus.OK);

    }

    // User registers a complaint(A ticket is generated) - to be changed to receive user email
    // after implementing oauth

    @PostMapping(path="/tickets/complaint")
    public ResponseEntity<HashMap<String, Object>> addComplaint(@RequestBody String description){

        TicketStructure complaint = new TicketStructure();
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


        // Sending mail to the user (Stating that the complaint ticket is generated)
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(complaint.getUsermail());
        simpleMailMessage.setSubject("HelpDesk optimus complaint generated");
        simpleMailMessage.setText("Your ticket with id "+complaint.getId()+" has been generated!. We will get back to you ASAP.");
        javaMailSender.send(simpleMailMessage);


        responseObject = new HashMap<>();
        responseObject.put("result", complaint);
        responseObject.put("errors", false);
        responseObject.put("message", "TicketStructure generated for complaint!");

        return new ResponseEntity<>(responseObject, HttpStatus.OK);


    }

    @PostMapping(path="/tickets/status/response",  consumes={"application/json"})
    public ResponseEntity<HashMap<String, Object>> sendResponse(@RequestBody Response response){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(response.getUsermail());
        simpleMailMessage.setSubject("HelpDesk optimus response mail");
        simpleMailMessage.setText("hello , this is your required response");
        simpleMailMessage.setText(response.getResponse());
        javaMailSender.send(simpleMailMessage);
        responseObject = new HashMap<>();
        responseObject.put("result", response);
        responseObject.put("errors", false);
        responseObject.put("message", "msg send");
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }


}
