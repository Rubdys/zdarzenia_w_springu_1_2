package com.example.zdarzenia_w_springu_1_2.controller;

import com.example.zdarzenia_w_springu_1_2.helper.CSVHelper;
import com.example.zdarzenia_w_springu_1_2.service.CsvConvertService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("v1/convert")
public class CsvConvertController {

    private final CSVHelper csvHelper;
    private final CsvConvertService csvConvertService;

    public CsvConvertController(CSVHelper csvHelper, CsvConvertService csvConvertService) {
        this.csvHelper = csvHelper;
        this.csvConvertService = csvConvertService;
    }

    @PostMapping(value = "personBirthdateToAge", produces = "text/csv")
     public void convertPersonBirthdateToAge(
            HttpServletResponse response,
            @RequestParam("file")MultipartFile file) throws Exception {
        String message = "";

        if(csvHelper.isCSV(file)){
            csvConvertService.convert(file, response);
        } else {
            message = "Please upload a csv file!";
            response.getWriter().println(message);
        }
    }
}
