package com.chatapp.chatappbackend.firebase.notification;

import com.google.firebase.messaging.AndroidConfig.Priority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    private Map<String, String> data;
    private Priority priority;
    private List<String> registrationTokenList;

}
