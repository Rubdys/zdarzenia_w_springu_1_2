package com.example.zdarzenia_w_springu_1_2.event;

import org.springframework.context.ApplicationEvent;

import java.math.BigDecimal;

public class CalculationEvent extends ApplicationEvent {
    private int x;
    private int y;
    private BigDecimal result;
    private String operationType;

    public CalculationEvent(Object source, int x, int y, BigDecimal result, String operationType) {
        super(source);
        this.x = x;
        this.y = y;
        this.result = result;
        this.operationType = operationType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BigDecimal getResult() {
        return result;
    }

    public String getOperationType(){
        return operationType;
    }
}
