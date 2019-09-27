package com.stackroute.helpdesk.ticketservice.controller;

import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.helpdesk.ticketservice.entity.TicketStructure;
import com.stackroute.helpdesk.ticketservice.repository.TicketRepository;
import com.stackroute.helpdesk.ticketservice.service.TicketInterface;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@Runwith(SprinRunner.class)
@RunWith(SpringRunner.class)
@WebMvcTest(value = TicketController.class)

public class TicketControllerTest {


    TicketStructure mockTicketStructure;
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private TicketController ticketController;

    private List openTicketList;
//    private ResponseEntity<HashMap<List, Object>> response;

//    @MockBean
//    private TicketRepository ticketRepository;

    @MockBean
    private TicketInterface ticketInterface;

    @Before
    public void setUp() throws Exception{

        MockitoAnnotations.initMocks(this);

    }
    //
    @Test
    public void getOpenTickets() throws Exception {

        openTicketList = new ArrayList<>();

        mockTicketStructure = new TicketStructure(
                "Get order details",
                "user1@gmail.com",
                "open",
                0,
                new Date(),
                new Date(),
                new Date(),
                Arrays.asList("info", "add"),
                "query",
                "bot",
                Arrays.asList("receipt", "invoice"));

          openTicketList.add(mockTicketStructure);
//

        when(ticketInterface.getOpenTickets()).thenReturn(openTicketList);
        mockMvc.perform(get("/tickets/open")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(mockTicketStructure)))
                .andExpect(status().isOk())
                .andDo(print());

//
    }

    @Test
    public void getAllTickets() throws Exception {

//        response = new ResponseEntity<HashMap<String, Object>>(openTicketList, HttpStatus.OK);


        when(ticketInterface.getTickets()).thenReturn(openTicketList);
        mockMvc.perform(get("/tickets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(mockTicketStructure)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    private static String jsonToString(final Object ob) throws JsonProcessingException {
        String result;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(ob);
            result = jsonContent;
        } catch(JsonProcessingException e) {
            result = "JSON processing error";
        }
        return result;
    }
}