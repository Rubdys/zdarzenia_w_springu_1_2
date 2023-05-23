package com.example.zdarzenia_w_springu_1_2.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InputPerson {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String name;
    private String surname;
    private LocalDate birthdate;

    public InputPerson(){
    }

    public InputPerson(String name, String surname, String birthdate) {
        this.name = name;
        this.surname = surname;
        this.birthdate = LocalDate.parse(birthdate, formatter);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = LocalDate.parse(birthdate, formatter);
    }
}
