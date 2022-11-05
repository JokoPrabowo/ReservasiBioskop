package org.binar.bioskop.services;

import org.binar.bioskop.dto.TicketModel;
import org.binar.bioskop.entities.TicketsEntity;

public interface TicketsService {
    public TicketsEntity create(TicketModel ticket);
    public TicketsEntity update(Integer id,TicketModel ticket);
    public TicketsEntity findOne(Integer id);
    public Iterable<TicketsEntity> findAll();
    public void delete(Integer id);
}
