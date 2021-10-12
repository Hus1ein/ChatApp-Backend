package com.chatapp.chatappbackend.services.implementations;

import com.chatapp.chatappbackend.rdb.entities.ChatEntity;
import com.chatapp.chatappbackend.rdb.entities.MessageEntity;
import com.chatapp.chatappbackend.rdb.models.Message;
import com.chatapp.chatappbackend.rdb.models.ReactionType;
import com.chatapp.chatappbackend.rdb.repositories.ChatsRepository;
import com.chatapp.chatappbackend.rdb.repositories.MessagesRepository;
import com.chatapp.chatappbackend.rdb.repositories.UsersRepository;
import com.chatapp.chatappbackend.services.interfaces.MessagesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MessagesServiceImpl implements MessagesService {

    private final MessagesRepository messagesRepository;
    private final UsersRepository usersRepository;
    private final ChatsRepository chatsRepository;

    @Override
    public List<Message> getAll(String userId, String chatId) {
        ChatEntity chatEntity = chatsRepository.findById(chatId).get();
        return chatEntity.getMessages().stream().map(MessageEntity::toModel).toList();
    }

    @Override
    public Message create(String userId, String chatId, Message message) {
        ChatEntity chatEntity = chatsRepository.findById(chatId).get();
        MessageEntity messageEntity = MessageEntity.builder()
                .id(UUID.randomUUID().toString())
                .content(message.getContent())
                .sentById(userId)
                .chatId(chatId)
                .sentAt(new Date())
                .reactions(new ArrayList<>())
                .build();
        messageEntity = messagesRepository.save(messageEntity);
        chatEntity.getMessages().add(messageEntity);
        chatsRepository.save(chatEntity);
        return messageEntity.toModel();
    }

    @Override
    public void react(String userId, String chatId, String messageId, ReactionType reactionType) {
        // find message and check if it belongs the chat.
        // check if user is a member of this chat.
        // create and save the reaction.
        // add this reaction to the message.
    }

}
