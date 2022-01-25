package com.chatapp.chatappbackend.firebase.notification;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.MulticastMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationsServiceImpl implements NotificationsService{

    @Override
    public void sendNotification(Notification notification) {
        AndroidConfig config = AndroidConfig.builder()
                .setPriority(notification.getPriority())
                .setTtl(1800 * 1000) //TODO Change this
                .build();

        MulticastMessage message = MulticastMessage.builder()
                .putAllData(notification.getData())
                .setAndroidConfig(config)
                .addAllTokens(notification.getRegistrationTokenList())
                .build();

        try {
            FirebaseMessaging.getInstance().sendMulticast(message);
        } catch (FirebaseMessagingException e) {
            log.error("An error occurred while sending a push notifications. Exception: {}", e.getMessage());
        }
    }

}
