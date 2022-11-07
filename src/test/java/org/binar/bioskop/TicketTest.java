package org.binar.bioskop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.binar.bioskop.dto.TicketModel;
import org.binar.bioskop.entities.TicketsEntity;
import org.binar.bioskop.repositories.TicketsRepo;
import org.binar.bioskop.services.impl.TicketsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest
public class TicketTest {
    
    @Autowired
    private TicketsServiceImpl ticketsServiceImpl;

    @MockBean
    private TicketsRepo ticketsRepo;

    @BeforeEach
    void setup(){
        Optional<TicketsEntity> ticket = Optional.of(new TicketsEntity(null, "user", null, 1, null, 'A', 1));
        Mockito.when(ticketsRepo.findById(1)).thenReturn(ticket);
    }

    @Test
    @DisplayName("Post Method - Create ticket")
    public void createTicket(){
        TicketsEntity data = new TicketsEntity(null, "user", null, 1, null, 'A', 1);
        Mockito.when(ticketsRepo.save(data)).thenReturn(data);
        assertEquals(data, ticketsRepo.save(data));
    }

    @Test
    @DisplayName("Put Method - Update ticket")
    public void updateTicket(){
        TicketsEntity data = new TicketsEntity(null, "user", null, 1, null, 'A', 2);
        TicketModel model = new TicketModel("user", 1, 'A', 2);
        Mockito.when(ticketsServiceImpl.update(1, model)).thenReturn(data);
        assertEquals(data, ticketsServiceImpl.update(1, model));
    }

    @Test
    @DisplayName("Get Method - Get ticket by id")
    public void getTicketById(){
        String user = "user";
        TicketsEntity ticket = ticketsServiceImpl.findOne(1);
        assertEquals(user, ticket.getUsername());
    }

    @Test
    @DisplayName("Get Method - Get all tickets")
    public void getAllTickets(){
        List<TicketsEntity> tickets = new ArrayList<>();
        assertEquals(tickets, ticketsServiceImpl.findAll());
    }
}
