package com.nhavronskyi.prostolearn.service;

import com.nhavronskyi.prostolearn.dto.Lesson;
import com.nhavronskyi.prostolearn.dto.Teacher;
import com.nhavronskyi.prostolearn.repository.LessonsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonsRepository lessonsRepository;

    public Lesson save(Lesson lesson) {
        if (lesson.isTimeValid() && !isLessonIsOverlapped(lesson)) {
            return lessonsRepository.save(lesson);
        }
        return null;
    }

    private boolean isLessonIsOverlapped(Lesson lesson) {
        Long id = Optional.ofNullable(lesson)
                .map(Lesson::getTeacher)
                .map(Teacher::getId)
                .orElseThrow(NullPointerException::new);
        List<Lesson> lessonsByTeacherId = lessonsRepository.findLessonsByTeacherId(id);
        return !lessonsByTeacherId.isEmpty() && lessonsByTeacherId.stream()
                .noneMatch(less -> less.isOverlapped(lesson));
    }

    public List<Lesson> getLessons() {
        return lessonsRepository.findAll();
    }
}
