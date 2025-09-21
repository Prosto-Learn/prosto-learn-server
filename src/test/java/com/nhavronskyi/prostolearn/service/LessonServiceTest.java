package com.nhavronskyi.prostolearn.service;

import com.nhavronskyi.prostolearn.dto.Lesson;
import com.nhavronskyi.prostolearn.dto.Teacher;
import com.nhavronskyi.prostolearn.dto.Timetable;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Set;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LessonServiceTest {
    @Autowired
    private LessonService lessonService;
    @Autowired
    private TeacherService teacherService;

    @Test
    @Order(0)
    void setUp() {
        Teacher teacher = new Teacher();
        teacher.setName("test");

        Timetable timetable = new Timetable();
        timetable.setStartTime(LocalTime.of(10, 0));
        timetable.setEndTime(LocalTime.of(18, 0));
        timetable.setWorkingDays(Set.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY));
        teacher.setTimetable(timetable);
        teacherService.saveTeacher(teacher);
    }

    @Test
    @Order(1)
    void teacherShouldExist() {
        Assertions.assertFalse(teacherService.getTeachers().isEmpty());
    }

    @Test
    @Order(2)
    @Transactional
    void createLesson() {
        LocalDateTime lessonStart = LocalDateTime.of(getNext(DayOfWeek.MONDAY), LocalTime.of(10, 0));
        LocalDateTime lessonEnd = lessonStart.plusHours(1);

        Lesson test = new Lesson();
        test.setName("test");
        test.setStartTime(lessonStart);
        test.setEndTime(lessonEnd);
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        test.setTeacher(teacher);
        Lesson save = lessonService.save(test);

        Assertions.assertEquals(test, save);
    }


    private static LocalDate getNext(DayOfWeek dayOfWeek) {
        LocalDate today = LocalDate.now();
        return today.with(TemporalAdjusters.nextOrSame(dayOfWeek));
    }
}