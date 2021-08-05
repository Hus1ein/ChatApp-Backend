package com.chatapp.chatappbackend.services.interfaces;

import com.chatapp.chatappbackend.rdb.models.Message;
import com.chatapp.chatappbackend.rdb.models.ReactionType;

import java.util.List;

public interface MessagesService {

    List<Message> getAll(String userId, String chatId);

    Message create(String userId, String chatId, Message message);

    void react(String userId, String chatId, String messageId, ReactionType reactionType);

}
