package com.example.zdarzenia_w_springu_1_2.controller;

import com.example.zdarzenia_w_springu_1_2.event.ProductCreationEvent;
import com.example.zdarzenia_w_springu_1_2.model.ProductDTO;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/products")
public class ProductController implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher publisher;

    @PostMapping(path = "createProduct")
    public void createProduct(@RequestBody ProductDTO productDTO) {
        System.out.println("Register product: " + productDTO.productName());
        publisher.publishEvent(
                new ProductCreationEvent(
                        this,
                        productDTO.productName(),
                        productDTO.otherData()
                )
        );
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
