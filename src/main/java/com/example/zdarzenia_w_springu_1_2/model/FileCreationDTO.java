package com.example.zdarzenia_w_springu_1_2.model;

import java.time.LocalDateTime;

public class FileCreationDTO {
    private String name;
    private LocalDateTime timeStamp;

    public FileCreationDTO(String name, LocalDateTime timeStamp) {
        this.name = name;
        this.timeStamp = timeStamp;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
}
