package com.chatapp.chatappbackend.rest.controllers;

import com.chatapp.chatappbackend.rdb.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UsersController {

    @GetMapping(path = "/{username}")
    public ResponseEntity<User> findByUsername(@AuthenticationPrincipal String principalName,
                                               @PathVariable(name = "username") String username) {
        return new ResponseEntity<>(new User(), HttpStatus.OK);
    }

    @GetMapping(path = "/contacts")
    public ResponseEntity<List<User>> getAllContacts(@AuthenticationPrincipal String principalName) {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

}
