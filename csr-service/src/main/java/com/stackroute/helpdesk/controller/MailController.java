package com.stackroute.helpdesk.controller;


import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class MailController {

    @Autowired
    private JavaMailSender javaMailSender;

    HashMap<String, Object> responseObject;

    @PostMapping(path = "/callbackmail")
    public ResponseEntity<HashMap<String, Object>> sendMsg(@RequestBody String email)
    {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("HelpDesk optimus callback mail");
        simpleMailMessage.setText("We have received your complaint request. We will get back to you shortly.");


        javaMailSender.send(simpleMailMessage);

        responseObject = new HashMap<>();
        responseObject.put("result", "Mail Success");
        responseObject.put("msg", "Mail sent!");
        responseObject.put("error", false);

        return new ResponseEntity<>(responseObject, HttpStatus.OK)  ;
    }
}
