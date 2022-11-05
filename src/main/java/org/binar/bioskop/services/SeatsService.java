package org.binar.bioskop.services;

import java.util.List;

import org.binar.bioskop.dto.SeatModel;
import org.binar.bioskop.entities.SeatId;
import org.binar.bioskop.entities.SeatsEntity;

public interface SeatsService {
    public SeatsEntity create(SeatModel seat);
    public SeatsEntity update(SeatId id, SeatModel seat);
    public SeatsEntity findOne(SeatId id);
    public List<SeatsEntity> findByStudioId(Integer id);
    public Iterable<SeatsEntity> findAll();
    public void delete(SeatId id);
}
