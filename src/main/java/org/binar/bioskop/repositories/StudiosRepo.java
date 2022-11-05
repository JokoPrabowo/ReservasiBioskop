package org.binar.bioskop.repositories;

import org.binar.bioskop.entities.StudiosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudiosRepo extends JpaRepository<StudiosEntity, Integer> {

}
