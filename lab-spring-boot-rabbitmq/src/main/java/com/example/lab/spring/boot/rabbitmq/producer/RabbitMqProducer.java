package com.example.lab.spring.boot.rabbitmq.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RabbitMqProducer {

    private final RabbitTemplate rabbitTemplate;

    public void produce(String message) {
        rabbitTemplate.convertAndSend("test.key", message);
    }
}
