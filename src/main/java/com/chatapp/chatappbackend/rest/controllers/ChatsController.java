package com.chatapp.chatappbackend.rest.controllers;

import com.chatapp.chatappbackend.rdb.models.Chat;
import com.chatapp.chatappbackend.rdb.models.User;
import com.chatapp.chatappbackend.services.interfaces.ChatsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/chats")
public class ChatsController {

    private final ChatsService chatsService;

    @GetMapping(path = "")
    public ResponseEntity<List<Chat>> getAll() {
        return new ResponseEntity<>(chatsService.getAll("1"), HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<Chat> create(@RequestBody Chat chat) {
        return new ResponseEntity<>(chatsService.create("1", chat), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{chatId}")
    public ResponseEntity update(@PathVariable(name = "chatId") String chatId,
                                 @RequestBody Chat chat) {
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/add-participant/{chatId}")
    public ResponseEntity addParticipant(@PathVariable(name = "chatId") String chatId,
                                         @RequestParam(name = "participantId") String participantId) {
        return ResponseEntity.noContent().build();
    }

}
