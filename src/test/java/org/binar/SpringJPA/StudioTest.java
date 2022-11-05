package org.binar.SpringJPA;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.binar.SpringJPA.entities.StudiosEntity;
import org.binar.SpringJPA.repositories.StudiosRepo;
import org.binar.SpringJPA.services.impl.StudiosServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class StudioTest {
    
    @Autowired
    private StudiosServiceImpl studiosServiceImpl;

    @MockBean
    private StudiosRepo studiosRepo;

    @BeforeEach
    void setup(){
        Optional<StudiosEntity> studio = Optional.of(new StudiosEntity(null, "Alpha"));
        Mockito.when(studiosRepo.findById(1)).thenReturn(studio);
    }

    @Test
    @DisplayName("Post Method - Create studio")
    public void createstudio(){
        StudiosEntity data = new StudiosEntity(null, "Alpha");
        Mockito.when(studiosRepo.save(data)).thenReturn(data);
        assertEquals(data, studiosRepo.save(data));
    }

    @Test
    @DisplayName("Put Method - Update studio")
    public void updatestudio(){
        StudiosEntity data = new StudiosEntity(null, "Omega");
        Mockito.when(studiosServiceImpl.update(1, data)).thenReturn(data);
        assertEquals(data, studiosServiceImpl.update(1, data));
    }

    @Test
    @DisplayName("Get Method - Get studio by id")
    public void getstudioById(){
        String name = "Alpha";
        StudiosEntity studio = studiosServiceImpl.findOne(1);
        assertEquals(name, studio.getStudioName());
    }

    @Test
    @DisplayName("Get Method - Get all studios")
    public void getAllstudios(){
        List<StudiosEntity> studios = new ArrayList<>();
        assertEquals(studios, studiosServiceImpl.findAll());
    }
}
