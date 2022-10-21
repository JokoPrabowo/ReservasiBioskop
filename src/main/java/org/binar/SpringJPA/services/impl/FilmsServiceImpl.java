package org.binar.SpringJPA.services.impl;

import org.binar.SpringJPA.entities.FilmsEntity;
import org.binar.SpringJPA.repositories.FilmsRepo;
import org.binar.SpringJPA.services.FilmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class FilmsServiceImpl implements FilmsService {
    @Autowired
    FilmsRepo filmsRepo;

    public FilmsEntity create(FilmsEntity film){
        log.info("Creating new film"); 
        try{
            log.info("Film has been created");
            return filmsRepo.save(film);
        }catch(Exception e){
            log.error("Error detected {}", e.getMessage());
            return null;
        }
    }
    public FilmsEntity update(String code, FilmsEntity film){
        log.info("Updating film data");
        try{
            FilmsEntity data = filmsRepo.findById(code).get();
            data.setFilmName(film.getFilmName());
            data.setCategory(film.getCategory());
            data.setOnShow(film.getOnShow());
            log.info("Film has been updated");
            return filmsRepo.save(data);
        }catch(Exception e){
            log.error("Error detected {}", e.getMessage());
            return null;
        }
    }
    public FilmsEntity findOne(String code){
        log.info("Retrieving the film");
        try{
            Optional<FilmsEntity> film= filmsRepo.findById(code);
            if (!film.isPresent()){
                log.info("Nothing was found");
                return null;
            }
            log.info("Film has been retrieved");
            return film.get();
        }catch(Exception e){
            log.error("Error detected {}", e.getMessage());
            return null;
        }
    }
    public Iterable<FilmsEntity> findAll(){
        log.info("Retrieving the films");
        return filmsRepo.findAll();
    }

    public List<FilmsEntity> isShowing(){
        log.info("Retrieving the films");
        return filmsRepo.findOnShowingFilms();
    }
    public void delete(String code){
        log.info("Deleting films");
        filmsRepo.deleteById(code);
    }
}
