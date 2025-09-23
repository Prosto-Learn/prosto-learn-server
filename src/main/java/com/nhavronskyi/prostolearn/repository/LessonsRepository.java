package com.nhavronskyi.prostolearn.repository;

import com.nhavronskyi.prostolearn.dto.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LessonsRepository extends JpaRepository<Lesson, Long> {
    @Query("SELECT l FROM lessons l where l.teacher.id = ?1")
    List<Lesson> findLessonsByTeacherId(Long id);
}
