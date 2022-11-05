package org.binar.bioskop.repositories;

import org.binar.bioskop.entities.FilmsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmsRepo extends JpaRepository<FilmsEntity, String> {

    @Query("SELECT f FROM FilmsEntity f WHERE f.onShow >= CURRENT_DATE")
    List<FilmsEntity> findOnShowingFilms();
}
