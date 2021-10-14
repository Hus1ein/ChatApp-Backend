package com.chatapp.chatappbackend.rabbitmq.payloads;

import com.chatapp.chatappbackend.rest.models.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewMessagePayload {

    Message message;
    List<String> usernameList;
    List<String> registrationTokenList;

}
