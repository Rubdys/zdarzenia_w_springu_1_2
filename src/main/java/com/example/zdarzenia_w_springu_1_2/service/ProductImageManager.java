package com.example.zdarzenia_w_springu_1_2.service;

import com.example.zdarzenia_w_springu_1_2.event.ProductCreationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ProductImageManager implements ApplicationListener<ProductCreationEvent> {

    @Override
    public void onApplicationEvent(ProductCreationEvent event) {
        System.out.println("Processing image of " + event.getProductName());
        System.out.println("The graphic is " + event.getOtherData());
    }
}
