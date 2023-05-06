package com.example.zdarzenia_w_springu_1_2.model;

public class CalculatorDTO {
    private int x;
    private int y;
    private String operation;

    public CalculatorDTO(int x, int y, String operation) {
        this.x = x;
        this.y = y;
        this.operation = operation;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getOperation() {
        return operation;
    }
}
