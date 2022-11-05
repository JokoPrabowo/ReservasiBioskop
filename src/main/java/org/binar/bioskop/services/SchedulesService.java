package org.binar.bioskop.services;

import java.util.List;

import org.binar.bioskop.dto.ScheduleModel;
import org.binar.bioskop.entities.SchedulesEntity;

public interface SchedulesService {
    public SchedulesEntity create(ScheduleModel schedule);
    public SchedulesEntity update(Integer id,ScheduleModel schedule);
    public SchedulesEntity findOne(Integer id);
    public List<SchedulesEntity> findByCode(String code);
    public Iterable<SchedulesEntity> findAll();
    public void delete(Integer id);
}
