package com.chatapp.chatappbackend.services.implementations;

import com.chatapp.chatappbackend.rdb.entities.UserEntity;
import com.chatapp.chatappbackend.rdb.repositories.UsersRepository;
import com.chatapp.chatappbackend.rest.exceptions.ItemNotFoundException;
import com.chatapp.chatappbackend.services.interfaces.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Override
    public UserEntity findById(String id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("The User: " + id + " doesn't exist"));
    }

    @Override
    public UserEntity findByUsername(String username) {
        return usersRepository.findByUsername(username)
                .orElseThrow(() -> new ItemNotFoundException("The User: " + username + " doesn't exist"));
    }

}
