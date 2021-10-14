package com.chatapp.chatappbackend.services.interfaces;

import com.chatapp.chatappbackend.rdb.entities.UserEntity;

public interface UsersService {

    UserEntity findById(String id);

    UserEntity findByUsername(String username);

}
