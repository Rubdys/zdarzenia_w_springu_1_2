package com.example.zdarzenia_w_springu_1_2.helper;

import com.example.zdarzenia_w_springu_1_2.model.BookDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BookCreator {
    private final RandomStringGenerator randomStringGenerator;
    private final RandomDateGenerator randomDateGenerator;

    public BookCreator(RandomStringGenerator randomStringGenerator, RandomDateGenerator randomDateGenerator) {
        this.randomStringGenerator = randomStringGenerator;
        this.randomDateGenerator = randomDateGenerator;
    }

    public BookDTO generateBook(){
        String name = randomStringGenerator.generateRandomString(20);
        String author = randomStringGenerator.generateRandomString(10);
        LocalDate minDate = LocalDate.of(2010, 1, 1);
        LocalDate maxDate = LocalDate.now();
        LocalDate creationDate = randomDateGenerator.generateRandomDateBetween(minDate, maxDate);

        return new BookDTO(name, author, creationDate);
    }
}
