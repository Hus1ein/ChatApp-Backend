package com.chatapp.chatappbackend.rdb.entities;

import com.chatapp.chatappbackend.rest.models.ChatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "chats")
public class ChatEntity {

    @Id
    private String id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private ProjectEntity project;
    @Enumerated(value = EnumType.STRING)
    private ChatType type;
    private Date createdAt;
    private String participant;
}
