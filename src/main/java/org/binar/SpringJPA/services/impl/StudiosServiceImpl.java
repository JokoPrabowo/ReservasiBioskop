package org.binar.SpringJPA.services.impl;

import org.binar.SpringJPA.entities.StudiosEntity;
import org.binar.SpringJPA.repositories.StudiosRepo;
import org.binar.SpringJPA.services.StudiosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@Service
public class StudiosServiceImpl implements StudiosService {
    @Autowired
    StudiosRepo studiosRepo;

    public StudiosEntity create(StudiosEntity studio){
        log.info("Creating new studio");
        try{
            log.info("Studio has been created");
            return studiosRepo.save(studio);
        }catch(Exception e){
            log.error("Error detected", e);
            return null;
        }
    }
    public StudiosEntity update(Integer id, StudiosEntity studiosEntity){
        log.info("Updating studio");
        try{
            StudiosEntity data = studiosRepo.findById(id).get();
            data.getStudioName();
            log.info("Studio has been updated");
            return studiosRepo.save(data);
        }catch(Exception e){
            log.error("Error detected", e);
            return null;
        }
    }
    public StudiosEntity findOne(Integer id){
        log.info("Retrieving the studio");
        try{
            Optional<StudiosEntity> studio = studiosRepo.findById(id);
            if (!studio.isPresent()){
                log.info("Nothing was found");
                return null;
            }
            log.info("Studio has been retrieved");
            return studio.get();
        }catch(Exception e){
            log.error("Error detected", e);
            return null;
        }
    }
    public Iterable<StudiosEntity> findAll(){
        log.info("Retrieving the studios");
        return studiosRepo.findAll();
    }
    public void delete(Integer id){
        log.info("Deleting studio");
        studiosRepo.deleteById(id);
    }
}
