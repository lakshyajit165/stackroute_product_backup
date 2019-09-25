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

    @PostMapping(path = "/sendEmail")
    public String sendMsg(@RequestBody String email)
    {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("callBack mail for ur problem");
        simpleMailMessage.setText("call on this no 1234567890 to resolve your queires");

        javaMailSender.send(simpleMailMessage);
        return "success.......................";
    }
}
