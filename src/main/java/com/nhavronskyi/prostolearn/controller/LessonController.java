package com.nhavronskyi.prostolearn.controller;

import com.nhavronskyi.prostolearn.dto.Lesson;
import com.nhavronskyi.prostolearn.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("lesson")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @PostMapping
    Lesson save(@RequestBody Lesson lesson) {
        return lessonService.save(lesson);
    }

    @GetMapping
    List<Lesson> getLessons() {
        return lessonService.getLessons();
    }
}
