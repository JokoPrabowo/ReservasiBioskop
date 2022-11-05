package org.binar.SpringJPA;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.binar.SpringJPA.entities.TicketsEntity;
import org.binar.SpringJPA.repositories.TicketsRepo;
import org.binar.SpringJPA.services.impl.TicketsServiceImpl;
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
    public void createticket(){
        TicketsEntity data = new TicketsEntity(null, "user", null, 1, null, 'A', 1);
        Mockito.when(ticketsRepo.save(data)).thenReturn(data);
        assertEquals(data, ticketsRepo.save(data));
    }

    @Test
    @DisplayName("Put Method - Update ticket")
    public void updateticket(){
        TicketsEntity data = new TicketsEntity(null, "user", null, 1, null, 'A', 2);
        Mockito.when(ticketsServiceImpl.update(1, data)).thenReturn(data);
        assertEquals(data, ticketsServiceImpl.update(1, data));
    }

    @Test
    @DisplayName("Get Method - Get ticket by id")
    public void getticketById(){
        String user = "user";
        TicketsEntity ticket = ticketsServiceImpl.findOne(1);
        assertEquals(user, ticket.getUsername());
    }

    @Test
    @DisplayName("Get Method - Get all tickets")
    public void getAlltickets(){
        List<TicketsEntity> tickets = new ArrayList<>();
        assertEquals(tickets, ticketsServiceImpl.findAll());
    }
}
