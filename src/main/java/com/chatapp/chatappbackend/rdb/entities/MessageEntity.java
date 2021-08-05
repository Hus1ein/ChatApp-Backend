package com.chatapp.chatappbackend.rdb.entities;

import com.chatapp.chatappbackend.rdb.models.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "messages")
public class MessageEntity {

    @Id
    private String id;
    private String content;
    private String sentById;
    private String chatId;
    private Date sentAt;
    @OneToMany(fetch = FetchType.EAGER)
    private List<ReactionEntity> reactions;
    /*
    @OneToMany()
    private List<String> seenBy;*/

    public Message toModel() {
        return Message.builder()
                .id(id)
                .content(content)
                .sentById(sentById)
                .sentAt(sentAt)
                .reactions(reactions.stream().map(ReactionEntity::toModel).toList())
                .build();
    }

}
