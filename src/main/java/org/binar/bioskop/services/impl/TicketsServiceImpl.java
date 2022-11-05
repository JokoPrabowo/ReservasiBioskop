package org.binar.bioskop.services.impl;

import org.binar.bioskop.dto.TicketModel;
import org.binar.bioskop.entities.TicketsEntity;
import org.binar.bioskop.repositories.TicketsRepo;
import org.binar.bioskop.services.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@Service
public class TicketsServiceImpl implements TicketsService {
    @Autowired
    TicketsRepo ticketsRepo;

    public TicketsEntity create(TicketModel ticket){
        log.info("Creating ticket data");
        try{
            TicketsEntity data = new TicketsEntity(null, ticket.getUsername(), null, ticket.getScheduleId(), null, ticket.getSeatRow(), ticket.getSeatNumber());
            log.info("Ticket data has been created");
            return ticketsRepo.save(data);
        }catch(Exception e){
            log.error("Error detected when creating ticket data {}", e.getMessage());
            return null;
        }
    }
    public TicketsEntity update(Integer id, TicketModel ticket){
        log.info("Updating ticket data");
        try{
            TicketsEntity data = findOne(id);
            data.setUsername(ticket.getUsername());
            data.setScheduleId(ticket.getScheduleId());
            data.setSeatRow(ticket.getSeatRow());
            data.setSeatNumber(ticket.getSeatNumber());
            log.info("Ticket data has been updated");
            return ticketsRepo.save(data);
        }catch(Exception e){
            log.error("Error detected when updating ticket data {}", e.getMessage());
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
