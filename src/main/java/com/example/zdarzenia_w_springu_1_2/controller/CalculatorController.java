package com.example.zdarzenia_w_springu_1_2.controller;

import com.example.zdarzenia_w_springu_1_2.event.CalculationEvent;
import com.example.zdarzenia_w_springu_1_2.model.CalculationResult;
import com.example.zdarzenia_w_springu_1_2.model.CalculatorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/v1/calculator")
@Slf4j
public class CalculatorController implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher applicationEventPublisher;

    @PostMapping(path = "add")
    public ResponseEntity<CalculationResult> add(@RequestBody CalculatorDTO calculatorDTO){
        log.info("Invoking add method from CalculatorController");
        BigDecimal result = new BigDecimal(calculatorDTO.getX() + calculatorDTO.getY());
        applicationEventPublisher.publishEvent(
                new CalculationEvent(
                        this,
                        calculatorDTO.getX(),
                        calculatorDTO.getY(),
                        result,
                        calculatorDTO.getOperation())
        );
        CalculationResult response = new CalculationResult(String.format("Addition result of %s and %s equals %s", calculatorDTO.getX(), calculatorDTO.getY(), result));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "subtract")
    public ResponseEntity<CalculationResult> subtract(@RequestBody CalculatorDTO calculatorDTO){
        log.info("Invoking subtract method from CalculatorController");
        BigDecimal result = new BigDecimal(calculatorDTO.getX() - calculatorDTO.getY());
        applicationEventPublisher.publishEvent(
                new CalculationEvent(
                        this,
                        calculatorDTO.getX(),
                        calculatorDTO.getY(),
                        result,
                        calculatorDTO.getOperation())
        );
        CalculationResult response = new CalculationResult(String.format("Subtraction result of %s and %s equals %s", calculatorDTO.getX(), calculatorDTO.getY(), result));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "multiply")
    public ResponseEntity<CalculationResult> multiply(@RequestBody CalculatorDTO calculatorDTO){
        log.info("Invoking multiply method from CalculatorController");
        BigDecimal result = new BigDecimal(calculatorDTO.getX() * calculatorDTO.getY());
        applicationEventPublisher.publishEvent(
                new CalculationEvent(
                        this,
                        calculatorDTO.getX(),
                        calculatorDTO.getY(),
                        result,
                        calculatorDTO.getOperation())
        );
        CalculationResult response = new CalculationResult(String.format("Multiplication result of %s and %s equals %s", calculatorDTO.getX(), calculatorDTO.getY(), result));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "divide")
    public ResponseEntity<CalculationResult> divide(@RequestBody CalculatorDTO calculatorDTO){
        log.info("Invoking divide method from CalculatorController");
        BigDecimal result = new BigDecimal(calculatorDTO.getX() / calculatorDTO.getY());
        applicationEventPublisher.publishEvent(
                new CalculationEvent(
                        this,
                        calculatorDTO.getX(),
                        calculatorDTO.getY(),
                        result,
                        calculatorDTO.getOperation())
        );
        CalculationResult response = new CalculationResult(String.format("Division result of %s and %s equals %s", calculatorDTO.getX(), calculatorDTO.getY(), result));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
