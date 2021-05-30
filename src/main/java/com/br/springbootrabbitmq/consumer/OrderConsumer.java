package com.br.springbootrabbitmq.consumer;

import com.br.springbootrabbitmq.config.MessagingConfig;
import com.br.springbootrabbitmq.dto.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderConsumer {
    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus){
        log.info("Message received from queue: " + orderStatus);
    }
}
