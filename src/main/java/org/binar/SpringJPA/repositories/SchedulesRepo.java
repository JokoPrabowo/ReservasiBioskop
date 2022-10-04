package org.binar.SpringJPA.repositories;

import java.util.List;

import org.binar.SpringJPA.entities.SchedulesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulesRepo extends JpaRepository<SchedulesEntity, Integer> {

    List<SchedulesEntity> findByFilmCode(String code);
}
