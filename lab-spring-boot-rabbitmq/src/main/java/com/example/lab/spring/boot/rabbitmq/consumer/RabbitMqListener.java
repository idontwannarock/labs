package com.example.lab.spring.boot.rabbitmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMqListener {

    @RabbitListener(queues = "test.queue")
    public void listen(@Payload String message) {
        log.info("message: {}", message);
    }
}
