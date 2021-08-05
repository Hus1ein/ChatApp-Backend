package com.chatapp.chatappbackend.rdb.entities;

import com.chatapp.chatappbackend.rdb.models.Chat;
import com.chatapp.chatappbackend.rdb.models.ChatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "chats")
public class ChatEntity {

    @Id
    private String id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private ChatType type;
    private String createdBy;
    private Date createdAt;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserEntity> participants;
    @OneToMany(fetch = FetchType.LAZY)
    private List<MessageEntity> messages;

    public Chat toModel() {
        return Chat.builder()
                .id(id)
                .name(name)
                .type(type)
                .createdBy(createdBy)
                .participants(participants.stream().map(UserEntity::toModel).toList())
                .build();
    }

}
