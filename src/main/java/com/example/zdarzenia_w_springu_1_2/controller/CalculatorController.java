package com.example.zdarzenia_w_springu_1_2.controller;

import com.example.zdarzenia_w_springu_1_2.event.CalculationEvent;
import com.example.zdarzenia_w_springu_1_2.model.CalculatorDTO;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/calculator")
public class CalculatorController implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher applicationEventPublisher;

    @PostMapping(path = "add")
    public String add(@RequestBody CalculatorDTO calculatorDTO){
        double result = calculatorDTO.x() + calculatorDTO.y();
        applicationEventPublisher.publishEvent(
                new CalculationEvent(
                        this,
                        calculatorDTO.x(),
                        calculatorDTO.y(),
                        result,
                        "Addition")
        );
        return String.format("Addition result of %s and %s equals %s", calculatorDTO.x(), calculatorDTO.y(), result);
    }

    @PostMapping(path = "subtract")
    public String subtract(@RequestBody CalculatorDTO calculatorDTO){
        double result = calculatorDTO.x() - calculatorDTO.y();
        applicationEventPublisher.publishEvent(
                new CalculationEvent(
                        this,
                        calculatorDTO.x(),
                        calculatorDTO.y(),
                        result,
                        "Subtraction")
        );
        return String.format("Subtraction result of %s and %s equals %s", calculatorDTO.x(), calculatorDTO.y(), result);
    }

    @PostMapping(path = "multiply")
    public String multiply(@RequestBody CalculatorDTO calculatorDTO){
        double result = calculatorDTO.x() * calculatorDTO.y();
        applicationEventPublisher.publishEvent(
                new CalculationEvent(
                        this,
                        calculatorDTO.x(),
                        calculatorDTO.y(),
                        result,
                        "Multiplication")
        );
        return String.format("Multiplication result of %s and %s equals %s", calculatorDTO.x(), calculatorDTO.y(), result);
    }

    @PostMapping(path = "divide")
    public String divide(@RequestBody CalculatorDTO calculatorDTO){
        double result = (double) calculatorDTO.x() / calculatorDTO.y();
        applicationEventPublisher.publishEvent(
                new CalculationEvent(
                        this,
                        calculatorDTO.x(),
                        calculatorDTO.y(),
                        result,
                        "Division")
        );
        return String.format("Division result of %s and %s equals %s", calculatorDTO.x(), calculatorDTO.y(), result);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
