package com.nhavronskyi.prostolearn.service;

import com.nhavronskyi.prostolearn.dto.Lesson;
import com.nhavronskyi.prostolearn.dto.Student;
import com.nhavronskyi.prostolearn.dto.Teacher;
import com.nhavronskyi.prostolearn.dto.Timetable;
import com.nhavronskyi.prostolearn.repository.LessonsRepository;
import com.nhavronskyi.prostolearn.repository.TimetableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonsRepository lessonsRepository;
    private final TimetableRepository timetableRepository;

    public Lesson save(Long studentId, Long teacherId, LocalDateTime startTime, LocalDateTime endTime, String lessonName) {
        Lesson lesson = new Lesson();
        lesson.setTeacher(Teacher.withId(teacherId));
        lesson.setStudent(Student.withId(studentId));
        lesson.setStartTime(startTime);
        lesson.setStartTime(endTime);
        lesson.setName(lessonName);
        return validateAndSave(lesson);
    }

    public Lesson save(Lesson lesson) {
        return save(lesson.getStudent().getId(), lesson.getTeacher().getId(), lesson.getStartTime(), lesson.getEndTime(), lesson.getName());
    }

    private Lesson validateAndSave(Lesson lesson) {
        Timetable timetable = timetableRepository.findById(lesson.getTeacher().getId())
                .orElseThrow(NullPointerException::new);
        if (timetable.isInWorkingDays(lesson)
            && timetable.isInWorkingHours(lesson)
            && lesson.isTimeValid()
            && !isLessonOverlapped(lesson)) {
            return lessonsRepository.save(lesson);
        }
        return null;
    }

    private boolean isLessonOverlapped(Lesson lesson) {
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
