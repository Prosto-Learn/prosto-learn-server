package com.nhavronskyi.prostolearn.service;

import com.nhavronskyi.prostolearn.dto.Lesson;
import com.nhavronskyi.prostolearn.dto.Teacher;
import com.nhavronskyi.prostolearn.dto.Timetable;
import jakarta.transaction.Transactional;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LessonServiceTest {
    @Autowired
    private LessonService lessonService;
    @Autowired
    private TeacherService teacherService;

    private static Lesson getLesson(DayOfWeek dayOfWeek, LocalTime localTime, String name) {
        LocalDateTime lessonStart = LocalDateTime.of(getNext(dayOfWeek), localTime);
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

    private static Lesson getLesson(DayOfWeek dayOfWeek, String name) {
        return getLesson(dayOfWeek, LocalTime.of(10, 0), name);
    }

    private static LocalDate getNext(DayOfWeek dayOfWeek) {
        LocalDate today = LocalDate.now();
        return today.with(TemporalAdjusters.next(dayOfWeek));
    }

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
        assertFalse(teacherService.getTeachers().isEmpty());
    }

    @Test
    @Order(2)
    @Transactional
    void createLesson() {
        Lesson test = getLesson(DayOfWeek.MONDAY, "test");

        lessonService.save(test);

        Lesson saving = lessonService.getLessonsByTeacher(1L).getFirst();

        assertEquals(test, saving);
    }

    @ParameterizedTest
    @CsvSource({
            "MONDAY, true",
            "WEDNESDAY, false"
    })
    @Order(3)
    @Transactional
    void shouldSaveOnlyInWorkingDays(DayOfWeek dayOfWeek, boolean status) {
        Lesson lesson = getLesson(dayOfWeek, dayOfWeek.name());
        lessonService.save(lesson);

        List<Lesson> lessons = lessonService.getLessonsByTeacher(1L);

        assertEquals(status, lessons.contains(lesson));
    }

    @Order(4)
    @ParameterizedTest
    @CsvSource({
            "11:00, true",
            "17:00, true",
            "08:00, false",
            "18:00, false",
            "22:00, false"
    })
    @Transactional
    void shouldSaveOnlyInWorkingHours(LocalTime start, boolean status) {
        DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
        Lesson lesson = getLesson(dayOfWeek, start, dayOfWeek.name());

        lessonService.save(lesson);

        List<Lesson> lessons = lessonService.getLessonsByTeacher(1L);

        assertEquals(status, lessons.contains(lesson));
    }
}