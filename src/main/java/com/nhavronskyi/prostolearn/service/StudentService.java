package com.nhavronskyi.prostolearn.service;

import com.nhavronskyi.prostolearn.dto.Student;
import com.nhavronskyi.prostolearn.repository.StudentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentsRepository studentsRepository;

    public Student saveStudent(Student student) {
        return studentsRepository.save(student);
    }

}
