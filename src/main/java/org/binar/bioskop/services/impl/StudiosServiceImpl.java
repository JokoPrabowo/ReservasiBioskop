package org.binar.bioskop.services.impl;

import org.binar.bioskop.dto.StudioModel;
import org.binar.bioskop.entities.StudiosEntity;
import org.binar.bioskop.repositories.StudiosRepo;
import org.binar.bioskop.services.StudiosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@Service
public class StudiosServiceImpl implements StudiosService {
    @Autowired
    StudiosRepo studiosRepo;

    public StudiosEntity create(StudioModel studio){
        log.info("Creating new studio");
        try{
            StudiosEntity data = new StudiosEntity(null, studio.getStudioName());
            log.info("Studio has been created");
            return studiosRepo.save(data);
        }catch(Exception e){
            log.error("Error detected when creating studio data {}", e.getMessage());
            return null;
        }
    }
    public StudiosEntity update(Integer id, StudioModel studio){
        log.info("Updating studio");
        try{
            StudiosEntity data = findOne(id);
            data.setStudioName(studio.getStudioName());
            log.info("Studio has been updated");
            return studiosRepo.save(data);
        }catch(Exception e){
            log.error("Error detected when updating studio data {}", e.getMessage());
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
            log.error("Error detected when retrieving studio data {}", e.getMessage());
            return null;
        }
    }
    public Iterable<StudiosEntity> findAll(){
        log.info("Retrieving the studios data");
        return studiosRepo.findAll();
    }
    public void delete(Integer id){
        log.info("Deleting studio data");
        studiosRepo.deleteById(id);
    }
}
