package com.stackroute.helpdesk.ticketservice.service;
import com.stackroute.helpdesk.ticketservice.exceptions.TicketNotFoundException;
import com.stackroute.helpdesk.ticketservice.entity.TicketStructure;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketInterface {

    public void saveTicketStatus(TicketStructure ticketStructure);

    public Optional<TicketStructure> getTicketById(String id);

    public List<TicketStructure> getTickets();

    public List<TicketStructure> getOpenTickets() throws TicketNotFoundException;
}
