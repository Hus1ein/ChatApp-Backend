package com.chatapp.chatappbackend.rdb.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    private String id;
    private String content;
    private String sentById;
    private Date sentAt;
    private List<Reaction> reactions;

}
