package com.nhavronskyi.prostolearn.controller;

import com.nhavronskyi.prostolearn.dto.Teacher;
import com.nhavronskyi.prostolearn.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("teacher")
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping
    List<Teacher> getAll(){
        return teacherService.getTeachers();
    }

    @PostMapping
    void saveTeacher(@RequestBody Teacher teacher){
        teacherService.saveTeacher(teacher);
    }
}
