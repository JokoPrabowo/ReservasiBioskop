package org.binar.SpringJPA.services.impl;

import org.binar.SpringJPA.entities.UsersEntity;
import org.binar.SpringJPA.repositories.UsersRepo;
import org.binar.SpringJPA.services.UsersService;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersRepo usersRepo;

    public UsersEntity create(UsersEntity user){
        Log.info("Creating new user data");
        try{
            Log.info("User data has been created");
            return usersRepo.save(user);
        }catch(Exception e){
            Log.info("Error detected",e);
            return null;
        }
    }
    public UsersEntity update(String username, UsersEntity usersEntity){
        Log.info("Updating user data");
        try{
            UsersEntity user = usersRepo.findById(username).get();
            user.setEmail(usersEntity.getEmail());
            user.setPassword(usersEntity.getPassword());
            Log.info("User data has been updated");
            return usersRepo.save(user);
        }catch(Exception e){
            Log.info("Error detected",e);
            return null;
        }
    }
    public UsersEntity findById(String username){
        Log.info("Retrieving user data");
        try{
            Optional<UsersEntity> user = usersRepo.findById(username);
            if(!user.isPresent()){
                Log.info("Nothing was found");
                return null;
            }
            Log.info("User data has been retrieved");
            return user.get();
        }catch(Exception e){
            Log.info("Error detected",e);
            return null;
        }
    }
    public Iterable<UsersEntity> findAll(){
        Log.info("Retrieving user data");
        return usersRepo.findAll();
    }
    public void delete(String username){
        Log.info("Deleting user data");
        usersRepo.deleteById(username);
    }
}
