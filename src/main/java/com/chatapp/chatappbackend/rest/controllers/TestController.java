package com.chatapp.chatappbackend.rest.controllers;

import com.chatapp.chatappbackend.rest.models.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchangeName;

    @Value("${rabbitmq.queue}")
    private String routingKey;

    @GetMapping("/register")
    public String test() {
        Message message = Message.builder()
                .id("123")
                .content("this is my first message")
                .build();
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
        return "String test";
    }

}
