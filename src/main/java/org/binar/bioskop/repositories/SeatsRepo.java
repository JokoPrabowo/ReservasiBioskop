package org.binar.bioskop.repositories;

import java.util.List;

import org.binar.bioskop.entities.SeatId;
import org.binar.bioskop.entities.SeatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatsRepo extends JpaRepository<SeatsEntity, SeatId> {
    List<SeatsEntity> findByStudioId(Integer id);
}
