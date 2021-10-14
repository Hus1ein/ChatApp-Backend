package com.chatapp.chatappbackend.rdb.repositories;

import com.chatapp.chatappbackend.rdb.entities.ChatEntity;
import com.chatapp.chatappbackend.rdb.entities.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatsRepository extends JpaRepository<ChatEntity, String> {

    List<ChatEntity> findAllByCreatedBy(UserEntity createdBy, Pageable pageable);

}
