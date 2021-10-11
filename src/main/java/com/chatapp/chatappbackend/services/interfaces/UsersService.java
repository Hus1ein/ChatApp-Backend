package com.chatapp.chatappbackend.services.interfaces;

import com.chatapp.chatappbackend.rdb.models.User;

public interface UsersService {

    User findByUsername(String username);

}
