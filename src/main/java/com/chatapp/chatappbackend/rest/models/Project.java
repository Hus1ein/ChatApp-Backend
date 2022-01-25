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
public class Project {

    private String id;
    private String name;
    private Date createdAt;
    private String apiKey;
    private String authenticationUrl;

}
