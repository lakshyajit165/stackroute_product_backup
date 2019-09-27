package com.stackroute.helpdesk.ticketservice.service;
import com.stackroute.helpdesk.ticketservice.exceptions.TicketNotFoundException;
import com.stackroute.helpdesk.ticketservice.entity.TicketStructure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketInterface {

    public List<TicketStructure> getTickets();

    public List<TicketStructure> getOpenTickets() throws TicketNotFoundException;
}
