package com.chatapp.chatappbackend.services.implementations;

import com.chatapp.chatappbackend.rdb.entities.ChatEntity;
import com.chatapp.chatappbackend.rdb.entities.MessageEntity;
import com.chatapp.chatappbackend.rdb.entities.UserEntity;
import com.chatapp.chatappbackend.rest.models.Message;
import com.chatapp.chatappbackend.rest.models.ReactionType;
import com.chatapp.chatappbackend.rdb.repositories.MessagesRepository;
import com.chatapp.chatappbackend.rest.exceptions.UnauthorizedException;
import com.chatapp.chatappbackend.services.interfaces.ChatsService;
import com.chatapp.chatappbackend.services.interfaces.MessagesService;
import com.chatapp.chatappbackend.services.interfaces.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MessagesServiceImpl implements MessagesService {

    private final MessagesRepository messagesRepository;
    private final UsersService usersService;
    private final ChatsService chatsService;

    @Override
    public List<Message> getAll(String username, String chatId, int pageNum) {
        usersService.findByUsername(username);
        ChatEntity chatEntity = chatsService.getById(chatId);
        boolean isNotParticipant = chatEntity.getParticipants()
                .stream()
                .noneMatch(participant -> participant.getUsername().equals(username));

        if (isNotParticipant) {
            throw new UnauthorizedException("Unauthorized access!");
        }

        Pageable messagesPage = PageRequest.of(pageNum, 10, Sort.by("sentAt").descending());
        return messagesRepository.findAllByChat(chatEntity, messagesPage)
                .stream()
                .map(MessageEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Message create(String username, String chatId, Message message) {
        UserEntity user = usersService.findByUsername(username);
        ChatEntity chatEntity = chatsService.getById(chatId);
        boolean isNotParticipant = chatEntity.getParticipants()
                .stream()
                .noneMatch(participant -> participant.getUsername().equals(username));

        if (isNotParticipant) {
            throw new UnauthorizedException("Unauthorized access!");
        }
        MessageEntity messageEntity = MessageEntity.builder()
                .id(UUID.randomUUID().toString())
                .content(message.getContent())
                .sentBy(user)
                .chat(chatEntity)
                .sentAt(new Date())
                .reactions(new ArrayList<>())
                .seenBy("")
                .build();
        messageEntity = messagesRepository.save(messageEntity);

        return messageEntity.toModel();
    }

    @Override
    public void react(String username, String chatId, String messageId, ReactionType reactionType) {
        // find message and check if it belongs the chat.
        // check if user is a member of this chat.
        // create and save the reaction.
        // add this reaction to the message.
    }

}
