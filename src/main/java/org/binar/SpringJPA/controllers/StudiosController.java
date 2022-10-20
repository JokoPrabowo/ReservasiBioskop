package org.binar.SpringJPA.controllers;

import org.binar.SpringJPA.dto.ResponseData;
import org.binar.SpringJPA.entities.StudiosEntity;
import org.binar.SpringJPA.services.impl.StudiosServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/studios")
public class StudiosController {
    @Autowired
    StudiosServiceImpl studiosServiceImpl;

    @Operation(summary = "Create a studio")
    @PostMapping("/create")
    public ResponseEntity<ResponseData> create(@RequestBody StudiosEntity studio){
        log.info("Processing studio data");
        try{
            ResponseData data = new ResponseData();
            data.setStatus("200");
            data.setMessagge("Studio successfully added");
            data.setData(studiosServiceImpl.create(studio));
            return ResponseEntity.ok(data);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Update a studio by its id")
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseData> update(@PathVariable Integer id, @RequestBody StudiosEntity studio){
        log.info("Processing studio data");
        try{
            ResponseData data = new ResponseData();
            data.setStatus("200");
            data.setMessagge("Studio successfully added");
            studiosServiceImpl.update(id, studio);
            data.setData(studiosServiceImpl.findOne(id));
            return ResponseEntity.ok(data);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Get all studios")
    @GetMapping("/get-all")
    public Iterable<StudiosEntity> findAll(){
        log.info("Processing studios data");
        return studiosServiceImpl.findAll();
    }

    @Operation(summary = "Get a studio by its id")
    @GetMapping("/get-one/{id}")
    public StudiosEntity findOne(@PathVariable Integer id){
        log.info("Processing studio data");
        return studiosServiceImpl.findOne(id);
    }

    @Operation(summary = "Delete a studio by its id")
    @DeleteMapping("/drop/{id}")
    public void delete(@PathVariable Integer id){
        log.info("Processing studio data");
        studiosServiceImpl.delete(id);
    }
}
