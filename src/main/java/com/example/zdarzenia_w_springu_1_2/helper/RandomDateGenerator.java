package com.example.zdarzenia_w_springu_1_2.helper;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class RandomDateGenerator {

    public LocalDate generateRandomDateBetween(LocalDate minDate, LocalDate maxDate){
        long minEpochDay = minDate.toEpochDay();
        long maxEpochDay = maxDate.toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(minEpochDay, maxEpochDay);
        return LocalDate.ofEpochDay(randomDay);
    }
}
