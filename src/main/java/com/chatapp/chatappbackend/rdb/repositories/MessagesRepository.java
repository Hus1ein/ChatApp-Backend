package com.chatapp.chatappbackend.rdb.repositories;

import com.chatapp.chatappbackend.rdb.entities.ChatEntity;
import com.chatapp.chatappbackend.rdb.entities.MessageEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagesRepository extends JpaRepository<MessageEntity, String> {

    List<MessageEntity> findAllByChat(ChatEntity chat, Pageable pageable);

}
