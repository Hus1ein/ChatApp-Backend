package com.chatapp.chatappbackend.rest.controllers;

import com.chatapp.chatappbackend.rest.models.Chat;
import com.chatapp.chatappbackend.services.interfaces.ChatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/chats")
@RequiredArgsConstructor
public class ChatsController {

    private final ChatsService chatsService;

    @GetMapping(path = "")
    public ResponseEntity<List<Chat>> getAll(@AuthenticationPrincipal String principalName,
                                             @RequestParam(name = "pageNum") int pageNum) {
        return new ResponseEntity<>(chatsService.getAll(principalName, pageNum), HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<Chat> create(@AuthenticationPrincipal String principalName,
                                       @RequestBody Chat chat) {
        return new ResponseEntity<>(chatsService.create(principalName, chat), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{chatId}")
    public ResponseEntity update(@AuthenticationPrincipal String principalName,
                                 @PathVariable(name = "chatId") String chatId,
                                 @RequestBody Chat chat) {
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/add-participant/{chatId}")
    public ResponseEntity addParticipant(@AuthenticationPrincipal String principalName,
                                         @PathVariable(name = "chatId") String chatId,
                                         @RequestParam(name = "participantId") String participantId) {
        return ResponseEntity.noContent().build();
    }

}
