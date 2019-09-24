package com.stackroute.helpdesk.repository;

import com.stackroute.helpdesk.entity.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, String> {

    @Query("{ 'status': 'open'}")
    public List<Ticket> getOpenTickets();
}
