package com.chatapp.chatappbackend.services.interfaces;

import com.chatapp.chatappbackend.rdb.entities.ChatEntity;
import com.chatapp.chatappbackend.rest.models.Chat;

import java.util.List;

public interface ChatsService {

    List<Chat> getAll(String username, int pageNum);

    ChatEntity getById(String id);

    Chat create(String userId, Chat chat);

    void put(String userId, String chatId, Chat chat);

    void addParticipant(String userId, String chatId, String participantId);

}
