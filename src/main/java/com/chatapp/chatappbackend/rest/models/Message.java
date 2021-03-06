package com.chatapp.chatappbackend.rest.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private String id;
    private String content;
    private String sentBy;
    private Date sentAt;

}
