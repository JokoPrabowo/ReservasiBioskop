package org.binar.SpringJPA;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.binar.SpringJPA.entities.SeatId;
import org.binar.SpringJPA.entities.SeatsEntity;
import org.binar.SpringJPA.repositories.SeatsRepo;
import org.binar.SpringJPA.services.impl.SeatsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class SeatTest {
    
     
    @Autowired
    private SeatsServiceImpl seatsServiceImpl;

    @MockBean
    private SeatsRepo seatsRepo;

    @BeforeEach
    void setup(){
        Optional<SeatsEntity> seat = Optional.of(new SeatsEntity(new SeatId('X', 1), 1, null, true));
        Mockito.when(seatsRepo.findById(new SeatId('X', 1))).thenReturn(seat);
    }

    @Test
    @DisplayName("Post Method - Create user")
    public void createUSer(){
        SeatsEntity data = new SeatsEntity(new SeatId('X', 1), 1, null, true);
        Mockito.when(seatsRepo.save(data)).thenReturn(data);
        assertEquals(data, seatsRepo.save(data));
    }

    @Test
    @DisplayName("Put Method - Update user")
    public void updateUser(){
        SeatsEntity data = new SeatsEntity(new SeatId('X', 1), 1, null, false);
        Mockito.when(seatsServiceImpl.update(new SeatId('X', 1), data)).thenReturn(data);
        assertEquals(data, seatsServiceImpl.update(new SeatId('X', 1), data));
    }

    @Test
    @DisplayName("Get Method - Get user by id")
    public void getUserById(){
        Boolean seatStatus = true;
        SeatsEntity seat = seatsServiceImpl.findOne(new SeatId('X', 1));
        assertEquals(seatStatus, seat.isSeatStatus());
    }

    @Test
    @DisplayName("Get Method - Get all users")
    public void getAllUsers(){
        List<SeatsEntity> seats = new ArrayList<>();
        assertEquals(seats, seatsServiceImpl.findAll());
    }
}
