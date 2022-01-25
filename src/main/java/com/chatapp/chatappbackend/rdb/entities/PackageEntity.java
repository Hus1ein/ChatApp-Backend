package com.chatapp.chatappbackend.rdb.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "packages")
public class PackageEntity {

    @Id
    private String id;
    private String name;
    private double price;

}
