package com.chatapp.chatappbackend.services.implementations;

import com.chatapp.chatappbackend.rdb.entities.ChatEntity;
import com.chatapp.chatappbackend.rdb.entities.UserEntity;
import com.chatapp.chatappbackend.rest.models.Chat;
import com.chatapp.chatappbackend.rdb.repositories.ChatsRepository;
import com.chatapp.chatappbackend.rest.exceptions.ItemNotFoundException;
import com.chatapp.chatappbackend.services.interfaces.ChatsService;
import com.chatapp.chatappbackend.services.interfaces.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatsServiceImpl implements ChatsService {

    private final ChatsRepository chatsRepository;
    private final UsersService usersService;

    @Override
    public List<Chat> getAll(String username, int pageNum) {
        log.info("Getting all chats by username: {}", username);
        UserEntity user = usersService.findByUsername(username);
        Pageable messagesPage = PageRequest.of(pageNum, 10);
        List<Chat> chatList = chatsRepository.findAllByCreatedBy(user, messagesPage)
                .stream()
                .map(ChatEntity::toModel)
                .toList();
        log.info("Chat list of");
        return chatList;
    }

    @Override
    public ChatEntity getById(String id) {
        return chatsRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("The Chat: " + id + " doesn't exist"));
    }

    @Override
    public Chat create(String username, Chat chat) {
        UserEntity userEntity = usersService.findByUsername(username);
        ChatEntity chatEntity = ChatEntity.builder()
                .id(UUID.randomUUID().toString())
                .name(chat.getName())
                .type(chat.getType())
                .createdBy(userEntity)
                .createdAt(new Date())
                .participants(List.of(userEntity))
                .build();
        chatEntity = chatsRepository.save(chatEntity);
        return chatEntity.toModel();
    }

    @Override
    public void put(String userId, String chatId, Chat chat) {

    }

    @Override
    public void addParticipant(String userId, String chatId, String participantId) {

    }

}
