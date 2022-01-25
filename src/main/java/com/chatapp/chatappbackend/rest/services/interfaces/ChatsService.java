package com.chatapp.chatappbackend.rest.services.interfaces;

import com.chatapp.chatappbackend.rdb.entities.ChatEntity;
import com.chatapp.chatappbackend.rest.models.Chat;

import java.util.List;

public interface ChatsService {

    List<Chat> getAll(String projectId, int pageNum);

    ChatEntity getById(String projectId, String chatId);

    ChatEntity getByUserId(String userId);

    Chat create(String projectId, String userId, Chat chat);

}
