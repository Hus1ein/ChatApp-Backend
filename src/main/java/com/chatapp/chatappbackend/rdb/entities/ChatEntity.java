package com.chatapp.chatappbackend.rdb.entities;

import com.chatapp.chatappbackend.rdb.models.Chat;
import com.chatapp.chatappbackend.rdb.models.ChatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "chats")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatEntity {

    @Id
    private String id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private ChatType type;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity createdBy;
    private Date createdAt;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserEntity> participants;

    public Chat toModel() {
        return Chat.builder()
                .id(id)
                .name(name)
                .type(type)
                .createdBy(createdBy.toModel())
                .participants(participants.stream().map(UserEntity::toModel).toList())
                .build();
    }

}
