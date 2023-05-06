package com.example.zdarzenia_w_springu_1_2.model;

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
}
