package org.binar.bioskop.controllers;

import java.util.ArrayList;
import java.util.List;

import org.binar.bioskop.dto.ResponseData;
import org.binar.bioskop.dto.SeatModel;
import org.binar.bioskop.dto.SeatRes;
import org.binar.bioskop.entities.SeatId;
import org.binar.bioskop.entities.SeatsEntity;
import org.binar.bioskop.services.impl.SeatsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/seats")
public class SeatsController {
    @Autowired
    SeatsServiceImpl seatsServiceImpl;

    @Operation(summary = "Create a new seat")
    @PostMapping("/create")
    public ResponseEntity<ResponseData> create(@RequestBody SeatModel seat){
       log.info("Processing to create seat data"); 
        try{
            ResponseData data = new ResponseData();
            SeatsEntity input = seatsServiceImpl.create(seat);
            SeatId id = input.getSeatId();
            SeatRes response = new SeatRes();
            response.setSeatNumber(id.getSeatNumber());
            response.setSeatRow(id.getSeatRow());
            response.setStudioId(input.getStudioId());
            response.setSeatStatus(input.isSeatStatus());
            data.setStatus("200");
            data.setMessagge("Seat successfully created");
            data.setData(response);
            return ResponseEntity.ok(data);
        }catch (Exception e){
            ResponseData data = new ResponseData();
            data.setStatus("400");
            data.setMessagge(e.getMessage());
            data.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }
    }

    @Operation(summary = "Get all seats")
    @GetMapping("/get-all")
    public Iterable<SeatsEntity> findAll(){
        log.info("Processing to retrieve seats data"); 
        return seatsServiceImpl.findAll();
    }

    @Operation(summary = "Get a seat by its id")
    @GetMapping("/get-one/{row}/{number}")
    public SeatsEntity findOne(@PathVariable Character row, @PathVariable Integer number){
        log.info("Processing to retrieve seat data by its id"); 
        return seatsServiceImpl.findOne(new SeatId(row, number));
    }

    @Operation(summary = "Get seats by StudioId")
    @GetMapping("/get-seat-bystudio/{id}")
    public ResponseEntity<ResponseData> findByStudioId(@PathVariable Integer id){
        log.info("Processing to retrieve seats data by studio id"); 
        try{
            ResponseData data = new ResponseData();
            List<SeatRes> list = new ArrayList<>();
            List<SeatsEntity> input = seatsServiceImpl.findByStudioId(id);
            input.stream().map(x -> {
                SeatRes response = new SeatRes();
                SeatId sid = x.getSeatId();
                response.setSeatNumber(sid.getSeatNumber());
                response.setSeatRow(sid.getSeatRow());
                response.setStudioId(x.getStudioId());
                response.setSeatStatus(x.isSeatStatus());
                return response;
            }).forEach(list::add);
            data.setStatus("200");
            data.setMessagge("Seat successfully retrieved");
            data.setData(list);
            return ResponseEntity.ok(data);
        }catch(Exception e){
            ResponseData data = new ResponseData();
            data.setStatus("400");
            data.setMessagge(e.getMessage());
            data.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }
    }

    @Operation(summary = "Update a seat by its id")
    @PutMapping("/update/{row}/{number}")
    public ResponseEntity<ResponseData> update(@PathVariable Character row, @PathVariable Integer number, @RequestBody SeatModel seat){
        log.info("Processing to update seat data"); 
        try{
            ResponseData data = new ResponseData();
            seatsServiceImpl.update(new SeatId(row, number), seat);
            SeatsEntity input = seatsServiceImpl.findOne(new SeatId(row, number));
            SeatId id = input.getSeatId();
            SeatRes response = new SeatRes();
            response.setSeatNumber(id.getSeatNumber());
            response.setSeatRow(id.getSeatRow());
            response.setStudioId(input.getStudioId());
            response.setSeatStatus(input.isSeatStatus());
            data.setStatus("200");
            data.setMessagge("Seat successfully updated");
            data.setData(response);
            return ResponseEntity.ok(data);
        }catch (Exception e){
            ResponseData data = new ResponseData();
            data.setStatus("400");
            data.setMessagge(e.getMessage());
            data.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }
    }

    @Operation(summary = "Delete a seat by its id")
    @DeleteMapping("/drop/{row}/{number}")
    public void delete(@PathVariable Character row, @PathVariable Integer number){
        log.info("Processing to delete seat data"); 
        seatsServiceImpl.delete(new SeatId(row, number));
    }
}
