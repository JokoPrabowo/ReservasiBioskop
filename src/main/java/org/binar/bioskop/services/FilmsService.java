package org.binar.bioskop.services;

import java.util.List;

import org.binar.bioskop.dto.FilmModel;
import org.binar.bioskop.entities.FilmsEntity;

public interface FilmsService {
    public FilmsEntity create(FilmModel film);
    public FilmsEntity update(String filmCode, FilmModel film);
    public FilmsEntity findOne(String code);
    public List<FilmsEntity> isShowing();
    public Iterable<FilmsEntity> findAll();
    public void delete(String code);
}
