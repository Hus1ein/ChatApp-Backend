package com.chatapp.chatappbackend.services.interfaces;

import com.chatapp.chatappbackend.rdb.models.Chat;

import java.util.List;

public interface ChatsService {

    List<Chat> getAll(String userId);

    Chat create(String userId, Chat chat);

    void put(String userId, String chatId, Chat chat);

    void addParticipant(String userId, String chatId, String participantId);

}
