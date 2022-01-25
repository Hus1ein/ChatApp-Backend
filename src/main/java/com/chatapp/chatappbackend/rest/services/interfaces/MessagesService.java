package com.chatapp.chatappbackend.rest.services.interfaces;

import com.chatapp.chatappbackend.rest.models.Message;

import java.util.List;

public interface MessagesService {

    List<Message> getAll(String username, String chatId, int pageNum);

    Message create(String username, String chatId, Message message);

    void react(String username, String chatId, String messageId);

}
