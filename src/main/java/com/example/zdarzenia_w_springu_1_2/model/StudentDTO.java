package com.example.zdarzenia_w_springu_1_2.model;

import com.example.zdarzenia_w_springu_1_2.validator.annotation.Range;

import javax.validation.constraints.Max;

public class StudentDTO {
    @Range(min = 1, max = 100)
    private int numberOfStudents;
    @Range(min = 5, max = 15)
    private int indexLength;

    public StudentDTO(int numberOfStudents, int indexLength) {
        this.numberOfStudents = numberOfStudents;
        this.indexLength = indexLength;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public int getIndexLength() {
        return indexLength;
    }
}
