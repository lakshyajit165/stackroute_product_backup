package com.stackroute.helpdesk.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class MailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping(path = "/callbackmail")
    public String sendMsg(@RequestBody String email)
    {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("HelpDesk optimus callback mail");
        simpleMailMessage.setText("We will get back to you shortly.");

        javaMailSender.send(simpleMailMessage);
        return "Mail Success!";
    }
}
