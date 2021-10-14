package com.chatapp.chatappbackend.rabbitmq.services;

import com.chatapp.chatappbackend.rabbitmq.payloads.NewMessagePayload;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SenderService {

    @Value("${rabbitmq.exchange}")
    private String exchangeName;

    @Value("${rabbitmq.queue}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendNewMessagePayload(NewMessagePayload payload) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, payload);
    }

}
