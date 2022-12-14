package org.binar.bioskop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.binar.bioskop.dto.StudioModel;
import org.binar.bioskop.entities.StudiosEntity;
import org.binar.bioskop.repositories.StudiosRepo;
import org.binar.bioskop.services.impl.StudiosServiceImpl;
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
    public void createStudio(){
        StudiosEntity data = new StudiosEntity(null, "Alpha");
        Mockito.when(studiosRepo.save(data)).thenReturn(data);
        assertEquals(data, studiosRepo.save(data));
    }

    @Test
    @DisplayName("Put Method - Update studio")
    public void updateStudio(){
        StudiosEntity data = new StudiosEntity(null, "Omega");
        StudioModel model = new StudioModel("Omega");
        Mockito.when(studiosServiceImpl.update(1, model)).thenReturn(data);
        assertEquals(data, studiosServiceImpl.update(1, model));
    }

    @Test
    @DisplayName("Get Method - Get studio by id")
    public void getStudioById(){
        String name = "Alpha";
        StudiosEntity studio = studiosServiceImpl.findOne(1);
        assertEquals(name, studio.getStudioName());
    }

    @Test
    @DisplayName("Get Method - Get all studios")
    public void getAllStudios(){
        List<StudiosEntity> studios = new ArrayList<>();
        assertEquals(studios, studiosServiceImpl.findAll());
    }
}
