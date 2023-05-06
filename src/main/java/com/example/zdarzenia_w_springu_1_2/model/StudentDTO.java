package com.example.zdarzenia_w_springu_1_2.model;

public class StudentDTO {
    private int numberOfStudents;
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
