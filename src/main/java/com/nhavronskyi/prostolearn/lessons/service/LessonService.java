package com.nhavronskyi.prostolearn.lessons.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nhavronskyi.prostolearn.lessons.dto.Lesson;
import com.nhavronskyi.prostolearn.lessons.repository.LessonsRepository;

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
