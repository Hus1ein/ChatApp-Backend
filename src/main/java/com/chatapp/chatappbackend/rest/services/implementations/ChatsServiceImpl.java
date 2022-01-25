package com.chatapp.chatappbackend.rest.services.implementations;

import com.chatapp.chatappbackend.rdb.entities.ChatEntity;
import com.chatapp.chatappbackend.rest.models.Chat;
import com.chatapp.chatappbackend.rdb.repositories.ChatsRepository;
import com.chatapp.chatappbackend.rest.services.interfaces.ChatsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatsServiceImpl implements ChatsService {

    private final ChatsRepository chatsRepository;

    @Override
    public List<Chat> getAll(String projectId, int pageNum) {
        return null;
    }

    @Override
    public ChatEntity getById(String projectId, String chatId) {
        return null;
    }

    @Override
    public ChatEntity getByUserId(String userId) {
        return null;
    }

    @Override
    public Chat create(String projectId, String userId, Chat chat) {
        return null;
    }
}
