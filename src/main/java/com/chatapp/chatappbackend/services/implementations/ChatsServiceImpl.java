package com.chatapp.chatappbackend.services.implementations;

import com.chatapp.chatappbackend.rdb.entities.ChatEntity;
import com.chatapp.chatappbackend.rdb.entities.UserEntity;
import com.chatapp.chatappbackend.rdb.models.Chat;
import com.chatapp.chatappbackend.rdb.repositories.ChatsRepository;
import com.chatapp.chatappbackend.rdb.repositories.UsersRepository;
import com.chatapp.chatappbackend.services.interfaces.ChatsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatsServiceImpl implements ChatsService {

    private final ChatsRepository chatsRepository;
    private final UsersRepository usersRepository;

    @Override
    public List<Chat> getAll(String userId) {
        log.info("Getting all chats by userId: {}", userId);
        List<Chat> chatList = chatsRepository.findAllByCreatedBy(userId)
                .stream()
                .map(ChatEntity::toModel)
                .toList();
        log.info("Chat list of");
        return chatList;
    }

    @Override
    public Chat create(String userId, Chat chat) {

        UserEntity userEntity = usersRepository.findById(userId).get();
        ChatEntity chatEntity = ChatEntity.builder()
                .id(UUID.randomUUID().toString())
                .name(chat.getName())
                .type(chat.getType())
                .createdBy(userId)
                .createdAt(new Date())
                .participants(List.of(userEntity))
                .messages(new ArrayList<>())
                .build();
        chatEntity = chatsRepository.save(chatEntity);
        userEntity.getChats().add(chatEntity);
        usersRepository.save(userEntity);
        return chatEntity.toModel();
    }

    @Override
    public void put(String userId, String chatId, Chat chat) {

    }

    @Override
    public void addParticipant(String userId, String chatId, String participantId) {

    }

}
