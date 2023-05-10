package com.example.zdarzenia_w_springu_1_2.controller;

import com.example.zdarzenia_w_springu_1_2.model.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/orders")
@Slf4j
public class OrderController {

    private final JmsTemplate jmsTemplate;

    public OrderController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @PostMapping(value = "receiveOrder")
    public void receiveOrder(@RequestBody OrderDTO order){
        log.info("Invoked receiveOrder method with " + order);
        jmsTemplate.convertAndSend("orders", order);
    }
}
