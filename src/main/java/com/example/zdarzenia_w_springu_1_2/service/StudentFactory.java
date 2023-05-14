package com.example.zdarzenia_w_springu_1_2.service;

import com.example.zdarzenia_w_springu_1_2.helper.RandomStringGenerator;
import com.example.zdarzenia_w_springu_1_2.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class StudentFactory {
    private RandomStringGenerator randomStringGenerator;

    public StudentFactory(RandomStringGenerator randomStringGenerator) {
        this.randomStringGenerator = randomStringGenerator;
    }

    public Map<String, Student> createStudentsAndReturnInMap(int numberOfStudents, int indexLength){
        Map<String, Student> studentMap = new HashMap<>();
        for(int i = 0; i < numberOfStudents; i++){
            Student student = createStudent(indexLength);
            putStudentInMap(studentMap, student);
        }
        return studentMap;
    }

    private Student createStudent(int indexLength){
        String randomIndexNumber = randomStringGenerator.generateRandomString(indexLength);
        return new Student(randomIndexNumber);
    }

    private Map<String, Student> putStudentInMap(Map<String,Student> studentMap, Student student){
        try {
            Field field = Student.class.getDeclaredField("indexNumber");
            field.setAccessible(true);
            String value = (String) field.get(student);
            studentMap.put(value, student);
            log.info("Put in map student with key {}", value);
            return studentMap;
        } catch (NoSuchFieldException e) {
            log.error("Field you try to access doesnt exist \n" + e.getMessage());
        } catch (IllegalAccessException e) {
            log.error("Can't access field \n" + e.getMessage());
        }
        throw new RuntimeException();
    }
}
