package com.chatapp.chatappbackend.rdb.entities;

import com.chatapp.chatappbackend.rdb.models.Reaction;
import com.chatapp.chatappbackend.rdb.models.ReactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
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
    private String messageId;
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
