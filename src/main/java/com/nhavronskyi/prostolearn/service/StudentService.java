package com.nhavronskyi.prostolearn.service;

import com.nhavronskyi.prostolearn.repository.StudentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentsRepository studentsRepository;
}
