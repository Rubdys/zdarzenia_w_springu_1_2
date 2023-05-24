package com.example.zdarzenia_w_springu_1_2.controller;

import com.example.zdarzenia_w_springu_1_2.model.FileCreationDTO;
import com.example.zdarzenia_w_springu_1_2.model.FilesNamesDTO;
import com.example.zdarzenia_w_springu_1_2.service.FileIntegrationService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("v1/fileIntegration")
public class FileIntegrationController {
    private final FileIntegrationService fileIntegrationService;

    public FileIntegrationController(FileIntegrationService fileIntegrationService) {
        this.fileIntegrationService = fileIntegrationService;
    }

    @PostMapping(value = "saveFile")
    public FileCreationDTO saveFile(@RequestParam("file") MultipartFile file){
        return fileIntegrationService.saveFile(file);
    }

    @GetMapping(value = "getFilesNames")
    public FilesNamesDTO getFilesNames(){
        return fileIntegrationService.getFilesNames();
    }
}
