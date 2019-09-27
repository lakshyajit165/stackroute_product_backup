package com.stackroute.helpdesk.ticketservice.repository;


import com.stackroute.helpdesk.ticketservice.entity.TicketStructure;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TicketRepository extends MongoRepository<TicketStructure, String> {

    @Query("{ 'status': 'open', 'type': 'query'}")
    public List<TicketStructure> getOpenTickets();
}
