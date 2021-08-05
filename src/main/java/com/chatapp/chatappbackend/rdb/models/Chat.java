package com.chatapp.chatappbackend.rdb.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chat {

    private String id;
    private String name;
    private ChatType type;
    private String createdBy;
    private List<User> participants;

}
