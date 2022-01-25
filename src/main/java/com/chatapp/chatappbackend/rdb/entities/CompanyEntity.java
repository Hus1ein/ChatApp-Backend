package com.chatapp.chatappbackend.rdb.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "companies")
public class CompanyEntity {

    @Id
    private String id;
    private String username;
    private String name;
    private Date createdAt;
    @OneToMany(fetch = FetchType.LAZY)
    private List<ProjectEntity> projects;
    @JoinColumn(name = "package")
    @ManyToOne(fetch = FetchType.LAZY)
    private PackageEntity packageEntity;
    private Date lastSubscribe;
    private Date subscribedUntil;

}
