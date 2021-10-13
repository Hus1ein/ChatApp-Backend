package com.chatapp.chatappbackend.rdb.entities;

import com.chatapp.chatappbackend.rdb.models.User;
import com.chatapp.chatappbackend.rdb.models.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String photo;
    @Enumerated(value = EnumType.STRING)
    private UserStatus status;
    private Date lastSeen;

    public User toModel() {
        return User.builder()
                .id(id)
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .photo(photo)
                .status(status)
                .lastSeen(lastSeen)
                .build();
    }

}
