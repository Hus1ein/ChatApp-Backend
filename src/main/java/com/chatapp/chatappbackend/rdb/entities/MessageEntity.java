package com.chatapp.chatappbackend.rdb.entities;

import com.chatapp.chatappbackend.rdb.models.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageEntity {

    @Id
    private String id;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity sentBy;
    @ManyToOne(fetch = FetchType.LAZY)
    private ChatEntity chat;
    private Date sentAt;
    @OneToMany(fetch = FetchType.EAGER)
    private List<ReactionEntity> reactions;
    private String seenBy;

    public Message toModel() {
        return Message.builder()
                .id(id)
                .content(content)
                .sentBy(sentBy.toModel())
                .chat(chat.toModel())
                .sentAt(sentAt)
                .reactions(reactions.stream().map(ReactionEntity::toModel).toList())
                .seenBy(Message.stringToList(seenBy))
                .build();
    }

}
