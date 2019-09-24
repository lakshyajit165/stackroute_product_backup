package com.stackroute.helpdesk.service;

import com.stackroute.helpdesk.entity.Ticket;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketInterface {

    public List<Ticket> getTickets();

    public List<Ticket> getOpenTickets();
}
