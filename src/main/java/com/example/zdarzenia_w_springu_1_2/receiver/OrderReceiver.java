package com.example.zdarzenia_w_springu_1_2.receiver;

import com.example.zdarzenia_w_springu_1_2.model.OrderDTO;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;

@Configuration
public class OrderReceiver {

    @JmsListener(containerFactory = "jmsFactory", destination = "orders")
    public void receive(OrderDTO order){
        System.out.println(order);
    }

}
