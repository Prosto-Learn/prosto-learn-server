package com.nhavronskyi.prostolearn.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nhavronskyi.prostolearn.dto.Lesson;
import com.nhavronskyi.prostolearn.service.LessonService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("lesson")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @PostMapping
    void save(@RequestBody Lesson lesson) {
        lessonService.save(lesson);
    }

    @GetMapping
    List<Lesson> getLessons() {
        return lessonService.getLessons();
    }
}
