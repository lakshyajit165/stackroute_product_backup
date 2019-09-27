package com.stackroute.helpdesk.ticketservice.service;

import com.stackroute.helpdesk.ticketservice.entity.TicketStructure;
import com.stackroute.helpdesk.ticketservice.exceptions.TicketNotFoundException;
import com.stackroute.helpdesk.ticketservice.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService implements TicketInterface{

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Optional<TicketStructure> getTicketById(String id){
        return ticketRepository.findById(id);
    }

    @Override
    public List<TicketStructure> getTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<TicketStructure> getOpenTickets() throws TicketNotFoundException {

        List<TicketStructure> openTickets = ticketRepository.getOpenTickets();

        if(openTickets.size() == 0){
            throw new TicketNotFoundException("No open tickets for now!");
        }else{
            return openTickets;
        }

        // return ticketRepository.getOpenTickets();
    }

    @Override
    public void changeStatusToEngaged(TicketStructure ticketStructure){
        ticketRepository.save(ticketStructure);
    }
}
