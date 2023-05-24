package com.example.zdarzenia_w_springu_1_2.model;

import java.util.List;

public class FilesNamesDTO {
    private List<String> filesNames;

    public FilesNamesDTO(List<String> filesNames) {
        this.filesNames = filesNames;
    }

    public List<String> getFilesNames() {
        return filesNames;
    }
}
