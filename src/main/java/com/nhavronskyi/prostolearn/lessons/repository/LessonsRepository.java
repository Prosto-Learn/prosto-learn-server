package com.nhavronskyi.prostolearn.lessons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nhavronskyi.prostolearn.lessons.dto.Lesson;

public interface LessonsRepository extends JpaRepository<Lesson, Long> {

}
