package org.binar.SpringJPA.services.impl;

import org.binar.SpringJPA.entities.SeatId;
import org.binar.SpringJPA.entities.SeatsEntity;
import org.binar.SpringJPA.repositories.SeatsRepo;
import org.binar.SpringJPA.services.SeatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SeatsServiceImpl implements SeatsService {
    @Autowired
    SeatsRepo seatsRepo;

    public SeatsEntity create(SeatsEntity seat){
        log.info("Creating new seat");
        try{
            seat.setSeatStatus(true);
            log.info("Seat has been created");
            return  seatsRepo.save(seat);   
        }catch(Exception e){
            log.error("Error detected", e);
            return null;
        }
    }
    public SeatsEntity update(SeatId id, SeatsEntity seat){
        log.info("Updating seat");
        try{
            SeatsEntity data = seatsRepo.findById(id).get();
            data.setSeatStatus(seat.isSeatStatus());
            log.info("Seat has been updated");
            return  seatsRepo.save(data);    
        }catch(Exception e){
            log.error("Error detected", e);
            return null;
        }
    }
    public SeatsEntity findOne(SeatId id){
        log.info("Retrieving the seat");
        try{
            Optional<SeatsEntity> seat = seatsRepo.findById(id);
            if (!seat.isPresent()){
                log.info("Nothin was found");
                return null;
            }
            log.info("Seat has been retrieved");
            return seat.get();   
        }catch(Exception e){
            log.error("Error detected", e);
            return null;
        }
    }
    public List<SeatsEntity> findByStudioId(Integer id){
        log.info("Retrieveing the seats");
        return seatsRepo.findByStudioId(id);
    }
    public Iterable<SeatsEntity> findAll(){
        log.info("Retrieveing the seats");
        return seatsRepo.findAll();
    }
    public void delete(SeatId id){
        log.info("Deleting the seat");
        seatsRepo.deleteById(id);
    }
}