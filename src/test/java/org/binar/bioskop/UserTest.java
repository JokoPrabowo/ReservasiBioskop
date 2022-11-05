package org.binar.bioskop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.binar.bioskop.dto.UserModel;
import org.binar.bioskop.entities.UsersEntity;
import org.binar.bioskop.repositories.UsersRepo;
import org.binar.bioskop.services.impl.UsersServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class UserTest {
    
    @Autowired
    private UsersServiceImpl usersServiceImpl;

    @MockBean
    private UsersRepo usersRepo;

    @BeforeEach
    void setup(){
        Optional<UsersEntity> user = Optional.of(new UsersEntity("user", "user@gmail.com", "user123"));
        Mockito.when(usersRepo.findById("user")).thenReturn(user);
    }

    @Test
    @DisplayName("Post Method - Create user")
    public void createUSer(){
        UsersEntity data = new UsersEntity("user", "user@gmail.com", "user123");
        Mockito.when(usersRepo.save(data)).thenReturn(data);
        assertEquals(data, usersRepo.save(data));
    }

    @Test
    @DisplayName("Put Method - Update user")
    public void updateUser(){
        UsersEntity data = new UsersEntity("user", "user@gmail.com", "user234");
        UserModel model = new UserModel("user", "user@gmail.com", "user234");
        Mockito.when(usersServiceImpl.update("user", model)).thenReturn(data);
        assertEquals(data, usersServiceImpl.update("user", model));
    }

    @Test
    @DisplayName("Get Method - Get user by id")
    public void getUserById(){
        String email = "user@gmail.com";
        UsersEntity user = usersServiceImpl.findById("user");
        assertEquals(email, user.getEmail());
    }

    @Test
    @DisplayName("Get Method - Get all users")
    public void getAllUsers(){
        List<UsersEntity> users = new ArrayList<>();
        assertEquals(users, usersServiceImpl.findAll());
    }
}
