package com.nhavronskyi.prostolearn.lessons.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nhavronskyi.prostolearn.lessons.dto.Lesson;
import com.nhavronskyi.prostolearn.lessons.service.LessonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("lesson")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @PostMapping
    void save(@RequestBody Lesson lesson){
        lessonService.save(lesson);
    }

    List<Lesson> getLessons(){
        return lessonService.getLessons();
    }
}
