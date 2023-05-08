package com.example.zdarzenia_w_springu_1_2.model;

import com.example.zdarzenia_w_springu_1_2.model.annotations.Convertable;

@Convertable
public class ProductDTO {
    private String productName;
    private String otherData;

    public ProductDTO(String productName, String otherData) {
        this.productName = productName;
        this.otherData = otherData;
    }

    public String getProductName() {
        return productName;
    }

    public String getOtherData() {
        return otherData;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productName='" + productName + '\'' +
                ", otherData='" + otherData + '\'' +
                '}';
    }
}
