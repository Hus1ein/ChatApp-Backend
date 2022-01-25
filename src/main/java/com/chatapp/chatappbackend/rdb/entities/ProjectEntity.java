package com.chatapp.chatappbackend.rdb.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "projects")
public class ProjectEntity {

    @Id
    private String id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private CompanyEntity company;
    private Date createdAt;
    private String apiKey;
    private String authenticationUrl;

}
