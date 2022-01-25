package com.chatapp.chatappbackend.rest.services.implementations;

import com.chatapp.chatappbackend.rest.models.Message;
import com.chatapp.chatappbackend.rdb.repositories.MessagesRepository;
import com.chatapp.chatappbackend.rest.services.interfaces.ChatsService;
import com.chatapp.chatappbackend.rest.services.interfaces.MessagesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MessagesServiceImpl implements MessagesService {

    private final MessagesRepository messagesRepository;
    private final ChatsService chatsService;

    @Override
    public List<Message> getAll(String username, String chatId, int pageNum) {
       return new ArrayList<>();
    }

    @Override
    public Message create(String username, String chatId, Message message) {


        return new Message();
    }

    @Override
    public void react(String username, String chatId, String messageId) {
        // find message and check if it belongs the chat.
        // check if user is a member of this chat.
        // create and save the reaction.
        // add this reaction to the message.
    }

}
