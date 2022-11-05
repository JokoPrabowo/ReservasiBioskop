package org.binar.bioskop.controllers;

import org.binar.bioskop.dto.FilmModel;
import org.binar.bioskop.dto.ResponseData;
import org.binar.bioskop.entities.FilmsEntity;
import org.binar.bioskop.services.impl.FilmsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/films")
public class FilmsController {
    @Autowired
    FilmsServiceImpl filmsServiceImpl;

    @Operation(summary = "create new film")
    @PostMapping("/create")
    public ResponseEntity<ResponseData> create(@RequestBody FilmModel film){
        log.info("Processing to create film data");
        try{
            ResponseData data = new ResponseData();
            data.setStatus("200");
            data.setMessagge("Film successfully added");
            data.setData(filmsServiceImpl.create(film));
            return ResponseEntity.ok(data);
        }catch (Exception e){
            ResponseData data = new ResponseData();
            data.setStatus("400");
            data.setMessagge(e.getMessage());
            data.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }
    }

    @Operation(summary = "Update a film")
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseData> update(@PathVariable String id, @RequestBody FilmModel film){
        log.info("Processing to update film data");
        try{
            ResponseData data = new ResponseData();
            data.setStatus("200");
            data.setMessagge("Film successfully updated");
            filmsServiceImpl.update(id, film);
            data.setData(filmsServiceImpl.findOne(id));
            return ResponseEntity.ok(data);
        }catch (Exception e){
            ResponseData data = new ResponseData();
            data.setStatus("400");
            data.setMessagge(e.getMessage());
            data.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }
    }

    @Operation(summary = "Get all films")
    @GetMapping("/get-all")
    public Iterable<FilmsEntity> findAll(){
        log.info("Processing films data");
        return filmsServiceImpl.findAll();
    }

    @Operation(summary = "Get a film by its id")
    @GetMapping("/get-one/{id}")
    public FilmsEntity findOne(@PathVariable String id){
        log.info("Processing film data");
        return filmsServiceImpl.findOne(id);
    }

    @Operation(summary = "Get showing films")
    @GetMapping("/get-showing")
    public List<FilmsEntity> isShowing(){
        log.info("Processing films data");
        return filmsServiceImpl.isShowing();
    }

    @Operation(summary = "Delete a film by its id")
    @DeleteMapping("/drop/{id}")
    public void delete(@PathVariable String id){
        log.info("Processing film data");
        filmsServiceImpl.delete(id);
    }
}
