package com.stackroute.helpdesk.service;

import com.stackroute.helpdesk.entity.Ticket;
import com.stackroute.helpdesk.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService implements TicketInterface{

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> getOpenTickets() {
        return ticketRepository.getOpenTickets();
    }
}
