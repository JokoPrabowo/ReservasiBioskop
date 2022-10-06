package org.binar.SpringJPA.controllers;

import org.binar.SpringJPA.dto.ResponseData;
import org.binar.SpringJPA.entities.FilmsEntity;
import org.binar.SpringJPA.services.impl.FilmsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/api/films")
public class FilmsController {
    @Autowired
    FilmsServiceImpl filmsServiceImpl;

    @Operation(summary = "create new film")
    @PostMapping("/create")
    public ResponseEntity<ResponseData> create(@RequestBody FilmsEntity film){
        try{
            ResponseData data = new ResponseData();
            data.setStatus("200");
            data.setMessagge("Film successfully added");
            data.setData(filmsServiceImpl.create(film));
            return ResponseEntity.ok(data);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Update a film")
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseData> update(@PathVariable String id, @RequestBody FilmsEntity film){
        try{
            ResponseData data = new ResponseData();
            data.setStatus("200");
            data.setMessagge("Film successfully updated");
            filmsServiceImpl.update(id, film);
            data.setData(filmsServiceImpl.findOne(id));
            return ResponseEntity.ok(data);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Get all films")
    @GetMapping("/get-all")
    public Iterable<FilmsEntity> findAll(){
        return filmsServiceImpl.findAll();
    }

    @Operation(summary = "Get a film by its id")
    @GetMapping("/get-one/{id}")
    public FilmsEntity findOne(@PathVariable String id){
        return filmsServiceImpl.findOne(id);
    }

    @Operation(summary = "Get showing films")
    @GetMapping("/get-showing")
    public List<FilmsEntity> isShowing(){
        return filmsServiceImpl.isShowing();
    }

    @Operation(summary = "Delete a film by its id")
    @DeleteMapping("/drop/{id}")
    public void delete(@PathVariable String id){
        filmsServiceImpl.delete(id);
    }
}
