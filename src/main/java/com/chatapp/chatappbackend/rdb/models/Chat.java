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
public class Chat {

    private String id;
    private String name;
    private ChatType type;
    private User createdBy;
    private Date createdAt;
    private List<User> participants;
    private List<Message> messages;

}
