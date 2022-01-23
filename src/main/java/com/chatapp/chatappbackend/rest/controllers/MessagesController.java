package com.chatapp.chatappbackend.rest.controllers;

import com.chatapp.chatappbackend.rest.models.Message;
import com.chatapp.chatappbackend.rest.models.ReactionType;
import com.chatapp.chatappbackend.services.interfaces.MessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/messages")
@RequiredArgsConstructor
public class MessagesController {

    private final MessagesService messagesService;

    @GetMapping(path = "/{chatId}")
    public ResponseEntity<List<Message>> getAll(@AuthenticationPrincipal String principalName,
                                                @PathVariable(name = "chatId") String chatId,
                                                @RequestParam(name = "pageNum") int pageNum) {
        return new ResponseEntity<>(messagesService.getAll(principalName, chatId, pageNum), HttpStatus.OK);
    }

    @PostMapping(path = "/{chatId}")
    public ResponseEntity<Message> create(@AuthenticationPrincipal String principalName,
                                          @PathVariable(name = "chatId") String chatId,
                                          @RequestBody Message message) {
        return new ResponseEntity<>(messagesService.create("+38762961404", chatId, message), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{messageId}/reactions")
    public ResponseEntity react(@AuthenticationPrincipal String principalName,
                                @PathVariable(name = "messageId") String messageId,
                                @RequestParam(name = "reactionType") ReactionType reactionType) {
        return ResponseEntity.noContent().build();
    }

}
