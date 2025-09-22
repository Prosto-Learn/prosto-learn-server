package com.nhavronskyi.prostolearn.service;

import com.nhavronskyi.prostolearn.dto.Lesson;
import com.nhavronskyi.prostolearn.dto.Teacher;
import com.nhavronskyi.prostolearn.dto.Timetable;
import com.nhavronskyi.prostolearn.repository.LessonsRepository;
import com.nhavronskyi.prostolearn.repository.TimetableRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonsRepository lessonsRepository;
    private final TimetableRepository timetableRepository;

    public Lesson save(Lesson lesson) {
        Timetable timetable = timetableRepository.findById(lesson.getTeacher().getId()).orElseThrow(NullPointerException::new);
        if (timetable.isInWorkingDays(lesson) && lesson.isTimeValid() && !isLessonIsOverlapped(lesson)) {
            return lessonsRepository.save(lesson);
        }
        return null;
    }

    private boolean isLessonIsOverlapped(Lesson lesson) {
        List<Lesson> lessonsByTeacherId = Optional.ofNullable(lesson)
                .map(Lesson::getTeacher)
                .map(Teacher::getId)
                .map(this::getLessonsByTeacher)
                .orElseThrow(NullPointerException::new);
        return !lessonsByTeacherId.isEmpty() && lessonsByTeacherId.stream()
                .noneMatch(less -> less.isOverlapped(lesson));
    }

    public List<Lesson> getLessons() {
        return lessonsRepository.findAll();
    }

    public List<Lesson> getLessonsByTeacher(Long id) {
        return lessonsRepository.findLessonsByTeacherId(id);
    }
}
