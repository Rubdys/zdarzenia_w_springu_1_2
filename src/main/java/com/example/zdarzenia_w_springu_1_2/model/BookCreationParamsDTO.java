package com.example.zdarzenia_w_springu_1_2.model;

public class BookCreationParamsDTO {
    private int numberOfBooks;

    public BookCreationParamsDTO() {
    }

    public BookCreationParamsDTO(int numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }

    public int getNumberOfBooks() {
        return numberOfBooks;
    }
}
