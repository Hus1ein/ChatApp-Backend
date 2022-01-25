package com.chatapp.chatappbackend.rdb.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageEntity {

    @Id
    private String id;
    private String content;
    private String sentBy;
    @ManyToOne(fetch = FetchType.LAZY)
    private ChatEntity chat;
    private Date sentAt;

}
