package com.chatapp.chatappbackend.rest.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    private String id;
    private String username;
    private String name;
    private List<Project> projects;
    private Package packageModel;
    private Date lastSubscribe;
    private Date subscribedUntil;

}
