package org.binar.bioskop.services.impl;

import org.binar.bioskop.dto.UserModel;
import org.binar.bioskop.entities.UsersEntity;
import org.binar.bioskop.repositories.UsersRepo;
import org.binar.bioskop.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersRepo usersRepo;

    public UsersEntity create(UserModel user){
        log.info("Creating new user data");
        try{
            UsersEntity data = new UsersEntity(user.getUsername(), user.getEmail(), user.getPassword());
            log.info("User data has been created");
            return usersRepo.save(data);
        }catch(Exception e){
            log.error("Error detected when creating user {}", e.getMessage());
            return null;
        }
    }
    public UsersEntity update(String username, UserModel user){
        log.info("Updating user data");
        try{
            UsersEntity data = findById(username);
            data.setEmail(user.getEmail());
            data.setPassword(user.getPassword());
            log.info("User data has been updated");
            return usersRepo.save(data);
        }catch(Exception e){
            log.error("Error detected when updating user data{}", e.getMessage());
            return null;
        }
    }
    public UsersEntity findById(String username){
        log.info("Retrieving user data");
        try{
            Optional<UsersEntity> user = usersRepo.findById(username);
            if(!user.isPresent()){
                log.info("Nothing was found");
                return null;
            }
            log.info("User data has been retrieved");
            return user.get();
        }catch(Exception e){
            log.error("Error detected when retrieving user data {}", e.getMessage());
            return null;
        }
    }
    public Iterable<UsersEntity> findAll(){
        log.info("Retrieving users data");
        return usersRepo.findAll();
    }
    public void delete(String username){
        log.info("Deleting user data");
        usersRepo.deleteById(username);
    }
}
