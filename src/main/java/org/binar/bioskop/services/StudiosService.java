package org.binar.bioskop.services;

import org.binar.bioskop.dto.StudioModel;
import org.binar.bioskop.entities.StudiosEntity;

public interface StudiosService {
    public StudiosEntity create(StudioModel studio);
    public StudiosEntity update(Integer id, StudioModel studio);
    public StudiosEntity findOne(Integer id);
    public Iterable<StudiosEntity> findAll();
    public void delete(Integer id);
}
