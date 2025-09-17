package com.nhavronskyi.prostolearn.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nhavronskyi.prostolearn.dto.Lesson;
import com.nhavronskyi.prostolearn.repository.LessonsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonsRepository repository;

    public void save(Lesson lesson) {
        repository.save(lesson);
    }

    public List<Lesson> getLessons() {
        return repository.findAll();
    }
}
