package org.binar.bioskop.services;

import org.binar.bioskop.dto.UserModel;
import org.binar.bioskop.entities.UsersEntity;

public interface UsersService {
    public UsersEntity create(UserModel user);
    public UsersEntity update(String username, UserModel user);
    public UsersEntity findById(String username);
    public Iterable<UsersEntity> findAll();
    public void delete(String username);
}
