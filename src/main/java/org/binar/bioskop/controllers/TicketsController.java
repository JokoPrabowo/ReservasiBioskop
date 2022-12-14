package org.binar.bioskop.controllers;

import java.io.ByteArrayInputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.binar.bioskop.dto.ResponseData;
import org.binar.bioskop.dto.SeatModel;
import org.binar.bioskop.dto.TicketData;
import org.binar.bioskop.dto.TicketModel;
import org.binar.bioskop.entities.*;
import org.binar.bioskop.services.impl.FilmsServiceImpl;
import org.binar.bioskop.services.impl.InvoiceServiceImpl;
import org.binar.bioskop.services.impl.SchedulesServiceImpl;
import org.binar.bioskop.services.impl.SeatsServiceImpl;
import org.binar.bioskop.services.impl.StudiosServiceImpl;
import org.binar.bioskop.services.impl.TicketsServiceImpl;
import org.binar.bioskop.services.impl.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/tickets")
public class TicketsController {

    @Autowired
    private TicketsServiceImpl ticketsServiceImpl;

    @Autowired
    private SeatsServiceImpl seatsServiceImpl;

    @Autowired
    private FilmsServiceImpl filmsServiceImpl;

    @Autowired
    private UsersServiceImpl usersServiceImpl;

    @Autowired
    private StudiosServiceImpl studiosServiceImpl;

    @Autowired
    private SchedulesServiceImpl schedulesServiceImpl;

    @Autowired
    private InvoiceServiceImpl invoiceServiceImpl;

    @Operation(summary = "Create a reservation")
    @PostMapping("/buy-ticket")
    public ResponseEntity<ResponseData> create(HttpServletResponse response, @RequestBody TicketModel ticket){
        log.info("Processing to create ticket data");
        try{
            ResponseData data = new ResponseData();
            TicketData tdata = new TicketData();
            UsersEntity user = usersServiceImpl.findById(ticket.getUsername());
            SchedulesEntity schedules = schedulesServiceImpl.findOne(ticket.getScheduleId());
            FilmsEntity film = filmsServiceImpl.findOne(schedules.getFilmCode());
            SeatsEntity seat = seatsServiceImpl.findOne(new SeatId(ticket.getSeatRow(), ticket.getSeatNumber()));
            SeatModel seatData = new SeatModel(seat.getSeatId().getSeatRow(), seat.getSeatId().getSeatNumber(), seat.getStudioId(), false);
            StudiosEntity studio = studiosServiceImpl.findOne(seat.getStudioId());
            seat.setSeatStatus(false);
            seatsServiceImpl.update(seat.getSeatId(),seatData);
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
            ByteArrayInputStream invoice = new ByteArrayInputStream(invoiceServiceImpl.generateFile(tdata));
            response.addHeader("Content-Disposition", "attachment; filename=" + tdata.getUsername() + ".pdf");
            IOUtils.copy(invoice, response.getOutputStream());
            response.flushBuffer();
            return ResponseEntity.ok(data);
        }catch (Exception e){
            ResponseData data = new ResponseData();
            data.setStatus("400");
            data.setMessagge(e.getMessage());
            data.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }
    }

    @Operation(summary = "Update a ticket")
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseData> update(@PathVariable Integer id, @RequestBody TicketModel ticket){
        log.info("Processing to update ticket data");
        try{
            ResponseData data = new ResponseData();
            data.setStatus("200");
            data.setMessagge("Ticket successfully updated");
            ticketsServiceImpl.update(id, ticket);
            data.setData(ticketsServiceImpl.findOne(id));
            return ResponseEntity.ok(data);
        }catch (Exception e){
            ResponseData data = new ResponseData();
            data.setStatus("400");
            data.setMessagge(e.getMessage());
            data.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }
    }

    @Operation(summary = "Get all tickets")
    @GetMapping("/get-all")
    public Iterable<TicketsEntity> findAll(){
        log.info("Processing to retrieve tickets data");
        return ticketsServiceImpl.findAll();
    }

    @Operation(summary = "Get a ticket by its id")
    @GetMapping("/get-one/{id}")
    public TicketsEntity findOne(@PathVariable Integer id){
        log.info("Processing to retrieve ticket data");
        return ticketsServiceImpl.findOne(id);
    }

    @Operation(summary = "Delete a ticket by its id")
    @DeleteMapping("/drop/{id}")
    public void delete(@PathVariable Integer id){
        log.info("Processing to delete ticket data");
        ticketsServiceImpl.delete(id);
    }
}
