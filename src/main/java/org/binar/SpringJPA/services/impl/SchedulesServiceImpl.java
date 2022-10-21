package org.binar.SpringJPA.services.impl;

import org.binar.SpringJPA.entities.SchedulesEntity;
import org.binar.SpringJPA.repositories.SchedulesRepo;
import org.binar.SpringJPA.services.SchedulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SchedulesServiceImpl implements SchedulesService {
    @Autowired
    SchedulesRepo schedulesRepo;

    public SchedulesEntity create(SchedulesEntity schedule){
        log.info("Creating new schedule");
        try{
            log.info("Schedule has been created");
            return schedulesRepo.save(schedule);
        }catch(Exception e){
            log.error("Error detected {}", e.getMessage());
            return null;
        }
    }
    public SchedulesEntity update(Integer id, SchedulesEntity schedule){
        log.info("Updating schedule");
        try{
            SchedulesEntity data = schedulesRepo.findById(id).get();
            data.getPrice();
            data.getShowDate();
            data.getStartAt();
            data.getEndAt();
            return schedulesRepo.save(data);
        }catch(Exception e){
            log.error("Error detected {}", e.getMessage());
            return null;
        }
    }
    public SchedulesEntity findOne(Integer id){
        log.info("Retrieving schedule data");
        try{
            Optional<SchedulesEntity> schedule = schedulesRepo.findById(id);
            if(!schedule.isPresent()){
                log.info("Nothing was found");
                return null;
            }
            log.info("Schedule has been retrieved");
            return schedule.get();
        }catch(Exception e){
            log.error("Error detected {}", e.getMessage());
            return null;
        }
    }
    public List<SchedulesEntity> findByCode(String code){
        log.info("Retrieving the schedules data");
        return schedulesRepo.findByFilmCode(code);
    }
    public Iterable<SchedulesEntity> findAll(){
        log.info("Retrieving the schedules data");
        return schedulesRepo.findAll();
    }
    public void delete(Integer id){
        log.info("Deleting the schedule");
        schedulesRepo.deleteById(id);
    }
}
