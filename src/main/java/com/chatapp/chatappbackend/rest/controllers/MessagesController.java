package com.chatapp.chatappbackend.rest.controllers;

import com.chatapp.chatappbackend.rdb.models.Message;
import com.chatapp.chatappbackend.rdb.models.ReactionType;
import com.chatapp.chatappbackend.services.interfaces.MessagesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/messages")
public class MessagesController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessagesService messagesService;

    @GetMapping(path = "/{chatId}")
    public ResponseEntity<List<Message>> getAll(@PathVariable(name = "chatId") String chatId) {
        return new ResponseEntity<>(messagesService.getAll("1", chatId), HttpStatus.OK);
    }

    @PostMapping(path = "/{chatId}")
    public ResponseEntity<Message> create(@PathVariable(name = "chatId") String chatId,
                                          @RequestBody Message message) {
        simpMessagingTemplate.convertAndSend("/topic/greetings", "Hiiii");
        return new ResponseEntity<>(messagesService.create("1", chatId, message), HttpStatus.CREATED);
    }

    @PutMapping(path = "/reaction/{messageId}")
    public ResponseEntity react(@PathVariable(name = "messageId") String messageId,
                                @RequestParam(name = "reactionType") ReactionType reactionType) {
        return ResponseEntity.noContent().build();
    }

}
