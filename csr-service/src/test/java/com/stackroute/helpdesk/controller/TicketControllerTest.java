package com.stackroute.helpdesk.controller;

import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.helpdesk.entity.Ticket;
import com.stackroute.helpdesk.repository.TicketRepository;
import com.stackroute.helpdesk.service.TicketInterface;
import com.stackroute.helpdesk.service.TicketService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@Runwith(SprinRunner.class)
@RunWith(SpringRunner.class)
@WebMvcTest(value = TicketController.class)

public class TicketControllerTest {

    Ticket mockTicket;
    @Autowired
    private MockMvc mockMvc;
//
    @InjectMocks
    private TicketController ticketController;

    private HashMap<String, Object> openTicketList;
    private ResponseEntity<HashMap<String, Object>> response;

    @MockBean
    private TicketRepository ticketRepository;

    @MockBean
    private TicketInterface ticketInterface;

    @Before
    public void setUp() throws Exception{

        MockitoAnnotations.initMocks(this);


    }
    //
    @Test
    public void getOpenTickets() throws Exception {

        openTicketList = new HashMap<>();

        mockTicket = new Ticket(
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

        openTicketList.put("result", mockTicket);
        openTicketList.put("errors", false);
        openTicketList.put("message", "Success");

        response = new ResponseEntity<>(openTicketList, HttpStatus.OK);


//        when(ticketController.getOpenTickets()).thenReturn(null);
        mockMvc.perform(get("/tickets/open")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(mockTicket)))
                .andExpect(status().isOk())
                .andDo(print());

//
    }

    @Test
    public void getAllTickets() throws Exception{

        response = new ResponseEntity<HashMap<String, Object>>(openTicketList, HttpStatus.OK);


//        when(ticketController.getOpenTickets()).thenReturn(null);
        mockMvc.perform(get("/tickets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(mockTicket)))
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