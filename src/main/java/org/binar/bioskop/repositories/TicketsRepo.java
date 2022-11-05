package org.binar.bioskop.repositories;

import org.binar.bioskop.entities.TicketsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketsRepo extends JpaRepository<TicketsEntity, Integer> {

}
