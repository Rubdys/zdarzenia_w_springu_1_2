package com.example.zdarzenia_w_springu_1_2.model;

import java.time.LocalDate;

public class BookDTO {
    String name;
    String author;
    LocalDate releaseDate;

    public BookDTO(String name, String author, LocalDate releaseDate) {
        this.name = name;
        this.author = author;
        this.releaseDate = releaseDate;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
