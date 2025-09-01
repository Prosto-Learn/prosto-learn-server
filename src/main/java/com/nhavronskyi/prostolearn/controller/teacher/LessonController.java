package com.nhavronskyi.prostolearn.controller.teacher;

import com.nhavronskyi.prostolearn.dto.Lesson;
import com.nhavronskyi.prostolearn.service.teacher.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("teacher")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @PostMapping("create-lesson")
    void createLesson(Lesson lesson){
        lessonService.createLesson(lesson);
    }

    @DeleteMapping("delete-lesson")
    Lesson deleteLesson(Lesson lesson){
        lessonService.deleteLesson(lesson);
        return lesson;
    }

    @PutMapping("edit-lesson")
    void editLesson(Lesson lesson){
        lessonService.editLesson(lesson);
    }
}
