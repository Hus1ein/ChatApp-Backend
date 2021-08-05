package com.chatapp.chatappbackend.rdb.entities;

import com.chatapp.chatappbackend.rdb.models.User;
import com.chatapp.chatappbackend.rdb.models.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "users")
public class UserEntity {

    @Id
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Enumerated(value = EnumType.STRING)
    private UserStatus status;
    private Date lastSeen;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<ChatEntity> chats;

    public User toModel() {
        return User.builder()
                .id(id)
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .status(status)
                .lastSeen(lastSeen)
                .build();
    }

}
