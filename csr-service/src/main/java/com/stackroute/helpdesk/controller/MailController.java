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
@CrossOrigin(origins="http://localhost:4200")
public class MailController {

    @Autowired
    private JavaMailSender javaMailSender;


    @Autowired
    private TicketRepository ticketRepository;

    HashMap<String, Object> responseObject;

    @PostMapping(path = "/callbackmail")
    public ResponseEntity<HashMap<String, Object>> sendMsg(@RequestBody String email)
    {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("HelpDesk optimus callback mail");
        simpleMailMessage.setText("We will get back to you regarding your query shortly.");


        javaMailSender.send(simpleMailMessage);

        responseObject = new HashMap<>();
        responseObject.put("result", "Mail Success");
        responseObject.put("msg", "Mail sent!");
        responseObject.put("error", false);

        return new ResponseEntity<>(responseObject, HttpStatus.OK)  ;
    }

    @PostMapping(path="/tickets/complaint")
    public ResponseEntity<HashMap<String, Object>> addComplaint(@RequestBody String description){

        Ticket complaint = new Ticket();
        complaint.setDescription(description);
        complaint.setUsermail("user1@gmail.com");
        complaint.setStatus("open");
        complaint.setRating(0);
        complaint.setTimeCreated(new Date());
        complaint.setTimeResolved(new Date());
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
