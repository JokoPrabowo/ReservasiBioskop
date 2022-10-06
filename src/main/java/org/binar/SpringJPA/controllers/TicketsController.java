package org.binar.SpringJPA.controllers;

import org.binar.SpringJPA.dto.ResponseData;
import org.binar.SpringJPA.dto.TicketData;
import org.binar.SpringJPA.entities.*;
import org.binar.SpringJPA.services.impl.FilmsServiceImpl;
import org.binar.SpringJPA.services.impl.SchedulesServiceImpl;
import org.binar.SpringJPA.services.impl.SeatsServiceImpl;
import org.binar.SpringJPA.services.impl.StudiosServiceImpl;
import org.binar.SpringJPA.services.impl.TicketsServiceImpl;
import org.binar.SpringJPA.services.impl.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/tickets")
public class TicketsController {

    @Autowired
    TicketsServiceImpl ticketsServiceImpl;

    @Autowired
    SeatsServiceImpl seatsServiceImpl;

    @Autowired
    FilmsServiceImpl filmsServiceImpl;

    @Autowired
    UsersServiceImpl usersServiceImpl;

    @Autowired
    StudiosServiceImpl studiosServiceImpl;

    @Autowired
    SchedulesServiceImpl schedulesServiceImpl;

    @Operation(summary = "Create a reservation")
    @PostMapping("/buy-ticket")
    public ResponseEntity<ResponseData> create(@RequestBody TicketsEntity ticket){
        try{
            ResponseData data = new ResponseData();
            TicketData tdata = new TicketData();
            UsersEntity user = usersServiceImpl.findById(ticket.getUsername());
            SchedulesEntity schedules = schedulesServiceImpl.findOne(ticket.getScheduleId());
            FilmsEntity film = filmsServiceImpl.findOne(schedules.getFilmCode());
            SeatsEntity seat = seatsServiceImpl.findOne(new SeatId(ticket.getSeatRow(), ticket.getSeatNumber()));
            StudiosEntity studio = studiosServiceImpl.findOne(seat.getStudioId());
            seat.setSeatStatus(false);
            seatsServiceImpl.update(seat.getSeatId(),seat);
            tdata.setUsername(user.getUsername());
            tdata.setFilm(film.getFilmName());
            tdata.setPrice(schedules.getPrice());
            tdata.setStudioName(studio.getStudioName());
            tdata.setSeatRow(ticket.getSeatRow());
            tdata.setSeatNumber(ticket.getSeatNumber());
            tdata.setShowDate(schedules.getShowDate());
            tdata.setStartAt(schedules.getStartAt());
            tdata.setEndAt(schedules.getEndAt());
            ticketsServiceImpl.create(ticket);
            data.setStatus("200");
            data.setMessagge("Ticket successfully reserved");
            data.setData(tdata);
            return ResponseEntity.ok(data);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Update a ticket")
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseData> update(@PathVariable Integer id, @RequestBody TicketsEntity ticket){
        try{
            ResponseData data = new ResponseData();
            data.setStatus("200");
            data.setMessagge("Ticket successfully updated");
            ticketsServiceImpl.update(id, ticket);
            data.setData(ticketsServiceImpl.findOne(id));
            return ResponseEntity.ok(data);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Get all tickets")
    @GetMapping("/get-all")
    public Iterable<TicketsEntity> findAll(){
        return ticketsServiceImpl.findAll();
    }

    @Operation(summary = "Get a ticket by its id")
    @GetMapping("/get-one/{id}")
    public TicketsEntity findOne(@PathVariable Integer id){
        return ticketsServiceImpl.findOne(id);
    }

    @Operation(summary = "Delete a ticket by its id")
    @DeleteMapping("/drop/{id}")
    public void delete(@PathVariable Integer id){
        ticketsServiceImpl.delete(id);
    }
}
