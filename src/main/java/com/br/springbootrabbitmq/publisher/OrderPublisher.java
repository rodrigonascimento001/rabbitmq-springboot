package com.br.springbootrabbitmq.publisher;

import com.br.springbootrabbitmq.config.MessagingConfig;
import com.br.springbootrabbitmq.dto.Order;
import com.br.springbootrabbitmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderPublisher {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/{restaurantName}")
    private String pubOrder(@RequestBody Order order, @PathVariable  String restaurantName){
        order.setOrderId(UUID.randomUUID().toString());
        // restaurant service
        // restaurant payment
        OrderStatus orderStatus = new OrderStatus(order, "PROCESS","order placed sucessfully in " + restaurantName);
        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, orderStatus);
        return "sucess!!";
    }
}
