package com.example.zdarzenia_w_springu_1_2.event;

import org.springframework.context.ApplicationEvent;

public class ProductCreationEvent extends ApplicationEvent {
    private String productName;
    private String otherData;

    public ProductCreationEvent(Object source, String productName, String otherData) {
        super(source);
        this.productName = productName;
        this.otherData = otherData;
    }

    public String getProductName() {
        return productName;
    }

    public String getOtherData() {
        return otherData;
    }
}
