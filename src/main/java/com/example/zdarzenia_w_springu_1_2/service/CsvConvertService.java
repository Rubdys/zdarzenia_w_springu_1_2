package com.example.zdarzenia_w_springu_1_2.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Files;

@Slf4j
@Service
public class CsvConvertService {

    public final String PATH = "C:\\Users\\OE06TJ\\IdeaProjects\\Kodilla\\Zdarzenia w Springu 1.2\\src\\main\\resources\\upload\\input.csv";
    private final JobLauncher jobLauncher;
    private final Job job;

    public CsvConvertService(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    public HttpServletResponse convert(MultipartFile file, HttpServletResponse response) throws Exception {
        try {
            File dir = new File(PATH);
            file.transferTo(dir);

            while (!new File(PATH).exists()) {
            }

            jobLauncher.run(job, new JobParameters());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=data.csv");
        byte[] outputCsv = Files.readAllBytes(new FileSystemResource("output.csv").getFile().toPath());
        ServletOutputStream out = response.getOutputStream();
        out.write(outputCsv);

        return response;
    }
}
