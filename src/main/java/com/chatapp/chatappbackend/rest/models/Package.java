package com.chatapp.chatappbackend.rest.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Package {

    private String id;
    private String name;
    private int price;

}
