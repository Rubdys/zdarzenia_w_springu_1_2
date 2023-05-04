package com.example.zdarzenia_w_springu_1_2.event;

import org.springframework.context.ApplicationEvent;

public class CalculationEvent extends ApplicationEvent {
    private int x;
    private int y;
    private double result;
    private String operationType;

    public CalculationEvent(Object source, int x, int y, double result, String operationType) {
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

    public double getResult() {
        return result;
    }

    public String getOperationType(){
        return operationType;
    }
}
