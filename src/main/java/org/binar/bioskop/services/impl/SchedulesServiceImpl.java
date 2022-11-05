package org.binar.bioskop.services.impl;

import org.binar.bioskop.dto.ScheduleModel;
import org.binar.bioskop.entities.SchedulesEntity;
import org.binar.bioskop.repositories.SchedulesRepo;
import org.binar.bioskop.services.SchedulesService;
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

    public SchedulesEntity create(ScheduleModel schedule){
        log.info("Creating new schedule");
        try{
            SchedulesEntity data = new SchedulesEntity(null, schedule.getFilmCode(), null, schedule.getStudioId(), null, schedule.getPrice(), schedule.getShowDate(), schedule.getStartAt(), schedule.getEndAt());
            log.info("Schedule has been created");
            return schedulesRepo.save(data);
        }catch(Exception e){
            log.error("Error detected when creating schedule data {}", e.getMessage());
            return null;
        }
    }
    public SchedulesEntity update(Integer id, ScheduleModel schedule){
        log.info("Updating schedule");
        try{
            SchedulesEntity data = findOne(id);
            data.setPrice(schedule.getPrice());
            data.setShowDate(schedule.getShowDate());
            data.setStartAt(schedule.getStartAt());
            data.setEndAt(schedule.getEndAt());
            return schedulesRepo.save(data);
        }catch(Exception e){
            log.error("Error detected when updating schedule data {}", e.getMessage());
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
            log.error("Error detected when retrieving schedule data {}", e.getMessage());
            return null;
        }
    }
    public List<SchedulesEntity> findByCode(String code){
        log.info("Retrieving the schedules data by film code");
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
