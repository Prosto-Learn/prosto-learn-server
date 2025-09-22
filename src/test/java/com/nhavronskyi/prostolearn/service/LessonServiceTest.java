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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
        Lesson test = getLesson(DayOfWeek.MONDAY, "test");

        lessonService.save(test);

        Lesson saving = lessonService.getLessonsByTeacher(1L).getFirst();

        Assertions.assertEquals(test, saving);
    }

    @ParameterizedTest
    @CsvSource({
            "MONDAY, true",
            "WEDNESDAY, false"
    })
    @Order(3)
    @Transactional
    void shouldSaveOnlyInWorkingDays(DayOfWeek dayOfWeek, boolean status){
        Lesson lesson = getLesson(dayOfWeek, dayOfWeek.name());
        lessonService.save(lesson);

        List<Lesson> lessons = lessonService.getLessonsByTeacher(1L);

        Assertions.assertEquals(status, lessons.contains(lesson));
    }

    private static Lesson getLesson(DayOfWeek dayOfWeek, String name) {
        LocalDateTime lessonStart = LocalDateTime.of(getNext(dayOfWeek), LocalTime.of(10, 0));
        LocalDateTime lessonEnd = lessonStart.plusHours(1);

        Lesson test = new Lesson();
        test.setName(name);
        test.setStartTime(lessonStart);
        test.setEndTime(lessonEnd);
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        test.setTeacher(teacher);
        return test;
    }


    private static LocalDate getNext(DayOfWeek dayOfWeek) {
        LocalDate today = LocalDate.now();
        return today.with(TemporalAdjusters.next(dayOfWeek));
    }
}