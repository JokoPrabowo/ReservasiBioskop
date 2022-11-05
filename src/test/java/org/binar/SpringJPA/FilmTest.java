package org.binar.SpringJPA;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.binar.SpringJPA.entities.FilmsEntity;
import org.binar.SpringJPA.repositories.FilmsRepo;
import org.binar.SpringJPA.services.impl.FilmsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class FilmTest {
    
    @Autowired
    private FilmsServiceImpl filmsServiceImpl;

    @MockBean
    private FilmsRepo filmsRepo;

    @BeforeEach
    void setup(){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Optional<FilmsEntity> film = Optional.of(new FilmsEntity("F0000", "Film Example", "Example", LocalDate.parse("28/12/2022", date)));
        Mockito.when(filmsRepo.findById("F0000")).thenReturn(film);
    }

    @Test
    @DisplayName("Post Method - Create film")
    public void createFilm(){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        FilmsEntity data = new FilmsEntity("F0000", "Film Example", "Example", LocalDate.parse("28/12/2022", date));
        Mockito.when(filmsRepo.save(data)).thenReturn(data);
        assertEquals(data, filmsRepo.save(data));
    }

    @Test
    @DisplayName("Put Method - Update film")
    public void updateUser(){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        FilmsEntity data = new FilmsEntity("F0000", "Film Example", "Example", LocalDate.parse("30/12/2022", date));
        Mockito.when(filmsServiceImpl.update("F0000", data)).thenReturn(data);
        assertEquals(data, filmsServiceImpl.update("F0000", data));
    }

    @Test
    @DisplayName("Get Method - Get film by id")
    public void getUserById(){
        String name = "Film Example";
        FilmsEntity film = filmsServiceImpl.findOne("F0000");
        assertEquals(name, film.getFilmName());
    }

    @Test
    @DisplayName("Get Method - Get all films")
    public void getAllUsers(){
        List<FilmsEntity> films = new ArrayList<>();
        assertEquals(films, filmsServiceImpl.findAll());
    }
}
