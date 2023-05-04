package com.example.zdarzenia_w_springu_1_2.service;

import com.example.zdarzenia_w_springu_1_2.event.CalculationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CalculatorManager implements ApplicationListener<CalculationEvent> {

    @Override
    public void onApplicationEvent(CalculationEvent event) {
        log.info("{} result of {} and {} is {}", event.getOperationType(), event.getX(), event.getY(), event.getResult());
    }
}
