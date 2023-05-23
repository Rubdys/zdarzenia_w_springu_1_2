package com.example.zdarzenia_w_springu_1_2.processor;

import com.example.zdarzenia_w_springu_1_2.model.InputPerson;
import com.example.zdarzenia_w_springu_1_2.model.OutputPerson;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;
import java.time.Period;

public class PersonProcessor implements ItemProcessor<InputPerson, OutputPerson> {
    @Override
    public OutputPerson process(InputPerson item) {
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(item.getBirthdate(), currentDate).getYears();
        return new OutputPerson(item.getName(), item.getSurname(), age);
    }
}
