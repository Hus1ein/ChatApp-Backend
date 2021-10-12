package com.chatapp.chatappbackend.services.implementations;

import com.chatapp.chatappbackend.rdb.entities.UserEntity;
import com.chatapp.chatappbackend.rdb.models.User;
import com.chatapp.chatappbackend.rdb.repositories.UsersRepository;
import com.chatapp.chatappbackend.services.interfaces.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Override
    public User findByUsername(String username) {
        UserEntity user = usersRepository.findByUsername(username).orElseThrow(() -> {throw new RuntimeException("Error");});
        return user.toModel();
    }

}
