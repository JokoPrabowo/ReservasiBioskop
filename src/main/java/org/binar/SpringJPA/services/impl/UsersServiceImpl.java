package org.binar.SpringJPA.services.impl;

import org.binar.SpringJPA.entities.UsersEntity;
import org.binar.SpringJPA.repositories.UsersRepo;
import org.binar.SpringJPA.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersRepo usersRepo;

    public UsersEntity create(UsersEntity user){
        log.info("Creating new user data");
        try{
            log.info("User data has been created");
            return usersRepo.save(user);
        }catch(Exception e){
            log.error("Error detected {}", e.getMessage());
            return null;
        }
    }
    public UsersEntity update(String username, UsersEntity usersEntity){
        log.info("Updating user data");
        try{
            UsersEntity user = usersRepo.findById(username).get();
            user.setEmail(usersEntity.getEmail());
            user.setPassword(usersEntity.getPassword());
            log.info("User data has been updated");
            return usersRepo.save(user);
        }catch(Exception e){
            log.error("Error detected {}", e.getMessage());
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
            log.error("Error detected {}", e.getMessage());
            return null;
        }
    }
    public Iterable<UsersEntity> findAll(){
        log.info("Retrieving user data");
        return usersRepo.findAll();
    }
    public void delete(String username){
        log.info("Deleting user data");
        usersRepo.deleteById(username);
    }
}
