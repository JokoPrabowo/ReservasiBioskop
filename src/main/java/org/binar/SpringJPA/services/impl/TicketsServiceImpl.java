package org.binar.SpringJPA.services.impl;

import org.binar.SpringJPA.entities.TicketsEntity;
import org.binar.SpringJPA.repositories.TicketsRepo;
import org.binar.SpringJPA.services.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@Service
public class TicketsServiceImpl implements TicketsService {
    @Autowired
    TicketsRepo ticketsRepo;

    public TicketsEntity create(TicketsEntity ticket){
        log.info("Creating ticket data");
        try{
            log.info("Ticket data has been created");
            return ticketsRepo.save(ticket);
        }catch(Exception e){
            log.error("Error detected", e);
            return null;
        }
    }
    public TicketsEntity update(Integer id, TicketsEntity ticket){
        log.info("Updating ticket data");
        try{
            TicketsEntity data = ticketsRepo.findById(id).get();
            log.info("Ticket data has been updated");
            return ticketsRepo.save(data);
        }catch(Exception e){
            log.error("Error detected", e);
            return null;
        }
    }
    public TicketsEntity findOne(Integer id){
        log.info("Retrieving the ticket data");
        Optional<TicketsEntity> ticket = ticketsRepo.findById(id);
        if (!ticket.isPresent()){
            log.info("Nothin was found");
            return null;
        }
        log.info("Ticket data has been retrieved");
        return ticket.get();
    }
    public Iterable<TicketsEntity> findAll(){
        log.info("Retrieving the tickets data");
        return ticketsRepo.findAll();
    }
    public void delete(Integer id){
        log.info("Deleting ticket data");
        ticketsRepo.deleteById(id);
    }
}
