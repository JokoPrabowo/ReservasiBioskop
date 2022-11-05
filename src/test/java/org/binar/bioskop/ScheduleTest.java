package org.binar.bioskop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.binar.bioskop.dto.ScheduleModel;
import org.binar.bioskop.entities.SchedulesEntity;
import org.binar.bioskop.repositories.SchedulesRepo;
import org.binar.bioskop.services.impl.SchedulesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ScheduleTest {

    @Autowired
    private SchedulesServiceImpl schedulesServiceImpl;

    @MockBean
    private SchedulesRepo schedulesRepo;

    @BeforeEach
    void setup(){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
        Optional<SchedulesEntity> schedule = Optional.of(new SchedulesEntity(null, "F0001", null, 1, null, 75000, LocalDate.parse("20/12/2022", date), LocalTime.parse("10:00:00", time), LocalTime.parse("12:30:00", time)));
        Mockito.when(schedulesRepo.findById(1)).thenReturn(schedule);
    }

    @Test
    @DisplayName("Post Method - Create schedule")
    public void createschedule(){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
        SchedulesEntity data = new SchedulesEntity(null, "F0001", null, 1, null, 75000, LocalDate.parse("20/12/2022", date), LocalTime.parse("10:00:00", time), LocalTime.parse("12:30:00", time));
        Mockito.when(schedulesRepo.save(data)).thenReturn(data);
        assertEquals(data, schedulesRepo.save(data));
    }

    @Test
    @DisplayName("Put Method - Update schedule")
    public void updateschedule(){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
        SchedulesEntity data = new SchedulesEntity(null, "F0001", null, 1, null, 75000, LocalDate.parse("20/12/2022", date), LocalTime.parse("11:00:00", time), LocalTime.parse("13:30:00", time));
        ScheduleModel model = new ScheduleModel("F0001", 1, 75000, LocalDate.parse("20/12/2022", date), LocalTime.parse("11:00:00", time), LocalTime.parse("13:30:00", time));
        Mockito.when(schedulesServiceImpl.update(1, model)).thenReturn(data);
        assertEquals(data, schedulesServiceImpl.update(1, model));
    }

    @Test
    @DisplayName("Get Method - Get schedule by id")
    public void getscheduleById(){
        String code = "F0001";
        SchedulesEntity schedule = schedulesServiceImpl.findOne(1);
        assertEquals(code, schedule.getFilmCode());
    }

    @Test
    @DisplayName("Get Method - Get all schedules")
    public void getAllschedules(){
        List<SchedulesEntity> schedules = new ArrayList<>();
        assertEquals(schedules, schedulesServiceImpl.findAll());
    }
}
