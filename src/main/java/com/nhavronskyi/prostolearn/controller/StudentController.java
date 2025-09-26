package com.nhavronskyi.prostolearn.controller;

import com.nhavronskyi.prostolearn.dto.Student;
import com.nhavronskyi.prostolearn.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public Student save(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }
}
