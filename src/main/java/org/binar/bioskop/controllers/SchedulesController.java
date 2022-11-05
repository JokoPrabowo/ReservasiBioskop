package org.binar.bioskop.controllers;

import java.util.ArrayList;
import java.util.List;

import org.binar.bioskop.dto.ResponseData;
import org.binar.bioskop.dto.ScheduleModel;
import org.binar.bioskop.dto.ScheduleRes;
import org.binar.bioskop.entities.SchedulesEntity;
import org.binar.bioskop.services.impl.SchedulesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/schedules")
public class SchedulesController {
    @Autowired
    SchedulesServiceImpl schedulesServiceImpl;

    @Operation(summary = "Create a schedule for a film")
    @PostMapping("/create")
    public ResponseEntity<ResponseData> create(@RequestBody ScheduleModel schedule){
        log.info("Processing to create schedule data");
        try{
            ResponseData data = new ResponseData();
            ScheduleRes response = new ScheduleRes();
            SchedulesEntity input = schedulesServiceImpl.create(schedule);
            response.setScheduleId(input.getScheduleId());
            response.setFilmCode(input.getFilmCode());
            response.setStudioId(input.getStudioId());
            response.setPrice(input.getPrice());
            response.setShowDate(input.getShowDate());
            response.setStartAt(input.getStartAt());
            response.setEndAt(input.getEndAt());
            data.setStatus("200");
            data.setMessagge("Schedule successfully created");
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

    @Operation(summary = "Get all schedules")
    @GetMapping("/get-all")
    public Iterable<SchedulesEntity> findAll(){
        log.info("Processing to retrieve schedules data");
        return schedulesServiceImpl.findAll();
    }

    @Operation(summary = "Get a schedule by its id")
    @GetMapping("/get-one/{id}")
    public SchedulesEntity findOne(@PathVariable Integer id){
        log.info("Processing to retrieve schedule data");
        return schedulesServiceImpl.findOne(id);
    }

    @Operation(summary = "Get schedules by its film code")
    @GetMapping("/get-film/{code}")
    public ResponseEntity<ResponseData> findByCode(@PathVariable String code){
        log.info("Processing to retrieve schedule data by film code");
        try{
            ResponseData data = new ResponseData();
            List<ScheduleRes> list = new ArrayList<>();
            List<SchedulesEntity> input = schedulesServiceImpl.findByCode(code);
            input.stream().map(x -> {
                ScheduleRes response = new ScheduleRes();
                response.setScheduleId(x.getScheduleId());
                response.setFilmCode(x.getFilmCode());
                response.setStudioId(x.getStudioId());
                response.setPrice(x.getPrice());
                response.setShowDate(x.getShowDate());
                response.setStartAt(x.getStartAt());
                response.setEndAt(x.getEndAt());
                return response;
            }).forEach(list::add);
            data.setStatus("200");
            data.setMessagge("Schedule successfully retrieved");
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

    @Operation(summary = "Update a schedule by its id")
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseData> update(@PathVariable Integer id, @RequestBody ScheduleModel schedule){
        log.info("Processing to retrieve schedule data");
        try{
            ResponseData data = new ResponseData();
            ScheduleRes response = new ScheduleRes();
            schedulesServiceImpl.update(id, schedule);
            SchedulesEntity input = schedulesServiceImpl.findOne(id);
            response.setScheduleId(input.getScheduleId());
            response.setFilmCode(input.getFilmCode());
            response.setStudioId(input.getStudioId());
            response.setPrice(input.getPrice());
            response.setShowDate(input.getShowDate());
            response.setStartAt(input.getStartAt());
            response.setEndAt(input.getEndAt());
            data.setStatus("200");
            data.setMessagge("Schedule successfully updated");
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

    @Operation(summary = "Delete a schedule by its id")
    @DeleteMapping("/drop/{id}")
    public void delete(@PathVariable Integer id){
        log.info("Processing to delete schedule data");
        schedulesServiceImpl.delete(id);
    }
}
