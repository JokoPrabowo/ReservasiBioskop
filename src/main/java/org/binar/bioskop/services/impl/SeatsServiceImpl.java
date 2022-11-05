package org.binar.bioskop.services.impl;

import org.binar.bioskop.dto.SeatModel;
import org.binar.bioskop.entities.SeatId;
import org.binar.bioskop.entities.SeatsEntity;
import org.binar.bioskop.repositories.SeatsRepo;
import org.binar.bioskop.services.SeatsService;
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

    public SeatsEntity create(SeatModel seat){
        log.info("Creating new seat");
        try{
            SeatsEntity data = new SeatsEntity(new SeatId(seat.getSeatRow(), seat.getSeatNumber()), seat.getStudioId(), null, true);
            log.info("Seat has been created");
            return  seatsRepo.save(data);   
        }catch(Exception e){
            log.error("Error detected when creating seat data {}", e.getMessage());
            return null;
        }
    }
    public SeatsEntity update(SeatId id, SeatModel seat){
        log.info("Updating seat");
        try{
            SeatsEntity data = findOne(id);
            data.setSeatStatus(seat.isSeatStatus());
            log.info("Seat has been updated");
            return  seatsRepo.save(data);    
        }catch(Exception e){
            log.error("Error detected when updating seat data {}", e.getMessage());
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
            log.error("Error detected when retrieving seat data {}", e.getMessage());
            return null;
        }
    }
    public List<SeatsEntity> findByStudioId(Integer id){
        log.info("Retrieveing the seats by studio id");
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