package com.example.zdarzenia_w_springu_1_2.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class CSVHelper {
    private final String TYPE = "text/csv";

    public boolean isCSV(MultipartFile file){
        return TYPE.equals(file.getContentType());
    }

}
