package com.example.zdarzenia_w_springu_1_2.service;

import com.example.zdarzenia_w_springu_1_2.model.FileCreationDTO;
import com.example.zdarzenia_w_springu_1_2.model.FilesNamesDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FileIntegrationService {
    private final String PATH_INPUT = "data/input";
    private final String FILE_OUTPUT = "data/output/list.txt";

    public FileCreationDTO saveFile(MultipartFile file) {
        File dir = new File(PATH_INPUT + "/" + file.getOriginalFilename());
        try {
            file.transferTo(dir.getAbsoluteFile());
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

        return new FileCreationDTO(file.getOriginalFilename(), LocalDateTime.now());
    }

    public FilesNamesDTO getFilesNames() {
        List<String> filesNames;
        try {
             filesNames = Files.readAllLines(Paths.get(FILE_OUTPUT));
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

        return new FilesNamesDTO(filesNames);
    }
}
