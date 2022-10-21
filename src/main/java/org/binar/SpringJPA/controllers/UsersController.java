package org.binar.SpringJPA.controllers;

import org.binar.SpringJPA.dto.ResponseData;
import org.binar.SpringJPA.entities.UsersEntity;
import org.binar.SpringJPA.services.impl.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private UsersServiceImpl usersServiceImpl;

    @Operation(summary = "Register new account")
    @PostMapping("/signup")
    public ResponseEntity<ResponseData> signup(@RequestBody UsersEntity user){
        log.info("Processing user data");
        try{
            ResponseData data = new ResponseData();
            data.setStatus("200");
            data.setMessagge("User successfully created");
            data.setData(usersServiceImpl.create(user));
            return ResponseEntity.ok(data);
        }catch (Exception e){
            ResponseData data = new ResponseData();
            data.setStatus("400");
            data.setMessagge(e.getMessage());
            data.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }
    }

    @Operation(summary = "Login to account")
    @GetMapping("/signin")
    public ResponseEntity<ResponseData> signin(@RequestBody UsersEntity user){
        log.info("Processing user data");
        try{
            ResponseData data = new ResponseData();
            UsersEntity usersEntity = usersServiceImpl.findById(user.getUsername());
            if(usersEntity.getPassword() != user.getPassword()){
                data.setStatus("400");
                data.setMessagge("Incorrect password");
                data.setData(null);
                return ResponseEntity.ok(data);
            }
            data.setStatus("200");
            data.setMessagge("User successfully signin");
            data.setData(usersEntity);
            return ResponseEntity.ok(data);
        }catch (Exception e){
            ResponseData data = new ResponseData();
            data.setStatus("400");
            data.setMessagge(e.getMessage());
            data.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }
    }

    @Operation(summary = "Update a user by its username")
    @PutMapping("/update/{username}")
    public ResponseEntity<ResponseData> update(@PathVariable String username, @RequestBody UsersEntity user){
        log.info("Processing user data");
        try{
            ResponseData data = new ResponseData();
            data.setStatus("200");
            data.setMessagge("User successfully updated");
            usersServiceImpl.update(username, user);
            data.setData(usersServiceImpl.findById(username));
            return ResponseEntity.ok(data);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
//    public UsersEntity update(@PathVariable String username, @RequestBody UsersEntity user){
//        return  usersServiceImpl.update(username, user);
//    }

    @Operation(summary = "Get all users")
    @GetMapping("/get-all")
    public Iterable<UsersEntity> findAll(){
        log.info("Processing users data");
        return usersServiceImpl.findAll();
    }

    @Operation(summary = "Get a user by its username")
    @GetMapping("/get-one/{username}")
    public UsersEntity findOne(@PathVariable String username){
        log.info("Processing user data");
        return usersServiceImpl.findById(username);
    }

    @Operation(summary = "Delete a user by its username")
    @DeleteMapping("/drop/{username}")
    public void delete(@PathVariable String username){
        log.info("Processing user data");
        usersServiceImpl.delete(username);
    }
}
