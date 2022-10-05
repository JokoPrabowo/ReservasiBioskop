package org.binar.SpringJPA.controllers;

import java.util.ArrayList;
import java.util.List;

import org.binar.SpringJPA.dto.ResponseData;
import org.binar.SpringJPA.dto.ScheduleRes;
import org.binar.SpringJPA.entities.SchedulesEntity;
import org.binar.SpringJPA.services.impl.SchedulesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedules")
public class SchedulesController {
    @Autowired
    SchedulesServiceImpl schedulesServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<ResponseData> create(@RequestBody SchedulesEntity schedule){
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/get-all")
    public Iterable<SchedulesEntity> findAll(){
        return schedulesServiceImpl.findAll();
    }

    @GetMapping("/get-one/{id}")
    public SchedulesEntity findOne(@PathVariable Integer id){
        return schedulesServiceImpl.findOne(id);
    }

    @GetMapping("/get-film/{code}")
    public ResponseEntity<ResponseData> findByCode(@PathVariable String code){
        try{
            ResponseData data = new ResponseData();
            List<ScheduleRes> list = new ArrayList<>();
            List<SchedulesEntity> input = schedulesServiceImpl.findByCode(code);
            input.stream().map((x) -> {
                ScheduleRes response = new ScheduleRes();
                response.setScheduleId(x.getScheduleId());
                response.setFilmCode(x.getFilmCode());
                response.setStudioId(x.getStudioId());
                response.setPrice(x.getPrice());
                response.setShowDate(x.getShowDate());
                response.setStartAt(x.getStartAt());
                response.setEndAt(x.getEndAt());
                return response;
            }).forEach((y) -> {
                list.add(y);
            });;
            data.setStatus("200");
            data.setMessagge("Schedule successfully retrieved");
            data.setData(list);
            return ResponseEntity.ok(data);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseData> update(@PathVariable Integer id, @RequestBody SchedulesEntity schedule){
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @DeleteMapping("/drop/{id}")
    public void delete(@PathVariable Integer id){
        schedulesServiceImpl.delete(id);
    }
}
