package com.example.zdarzenia_w_springu_1_2.controller;

import com.example.zdarzenia_w_springu_1_2.model.Student;
import com.example.zdarzenia_w_springu_1_2.model.StudentDTO;
import com.example.zdarzenia_w_springu_1_2.service.StudentFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1/student")
public class StudentController {
    private StudentFactory studentFactory;

    public StudentController(StudentFactory studentFactory) {
        this.studentFactory = studentFactory;
    }

    @PostMapping(path = "createStudents")
    public void createStudents(@RequestBody StudentDTO studentDTO){
        Map<String, Student> studentMap = studentFactory.createStudentsAndReturnInMap(studentDTO.getNumberOfStudents(), studentDTO.getIndexLength());
        System.out.println(studentMap);
    }
}
