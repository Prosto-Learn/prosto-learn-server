package com.nhavronskyi.prostolearn.service;

import com.nhavronskyi.prostolearn.dto.Teacher;
import com.nhavronskyi.prostolearn.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public List<Teacher> getTeachers(){
        return teacherRepository.findAll();
    }

    public void saveTeacher(Teacher teacher){
        teacherRepository.save(teacher);
    }
}
