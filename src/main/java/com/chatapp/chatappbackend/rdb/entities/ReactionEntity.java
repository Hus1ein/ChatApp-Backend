package com.chatapp.chatappbackend.rdb.entities;

import com.chatapp.chatappbackend.rest.models.Reaction;
import com.chatapp.chatappbackend.rest.models.ReactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "reactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReactionEntity {

    @Id
    private String id;
    private String reactedBy;
    @ManyToOne(fetch = FetchType.LAZY)
    private MessageEntity message;
    private Date reactedAt;
    @Enumerated(value = EnumType.STRING)
    private ReactionType type;

    public Reaction toModel() {
        return Reaction.builder()
                .id(id)
                .reactedBy(reactedBy)
                .type(type)
                .build();
    }

}
